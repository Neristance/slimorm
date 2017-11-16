package io.dominikschulz.tinyorm;


import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

import io.dominikschulz.tinyorm.Field;

@SupportedAnnotationTypes({
        "io.dominikschulz.tinyorm.Field"
})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TinyOrmProcessor extends AbstractProcessor {

    private static final ClassName LIST_TYPE = ClassName.get("java.util", "List");
    private static final ClassName ARRAY_LIST_TYPE = ClassName.get("java.util", "ArrayList");
    private static final ArrayTypeName BYTE_ARRAY_TYPE = ArrayTypeName.of(TypeName.BYTE);
    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Map<TypeElement, List<VariableElement>> toBeProcessed = new HashMap<>();

        for (Element element : roundEnv.getElementsAnnotatedWith(Field.class)) {

            if (element.getKind() == ElementKind.FIELD) {

                if (element.getModifiers().contains(Modifier.PRIVATE)) {
                    throwError("TinyOrm can only work with package protected fields");
                }

                final Element enclosingElement = element.getEnclosingElement();
                if (enclosingElement.getKind() == ElementKind.CLASS) {
                    final TypeElement classElement = (TypeElement) enclosingElement;

                    if (!toBeProcessed.containsKey(classElement)) {
                        toBeProcessed.put(classElement, new ArrayList<VariableElement>());
                    }

                    List<VariableElement> variableElements = toBeProcessed.get(classElement);
                    variableElements.add((VariableElement) element);
                }

            }
        }

        for (Map.Entry<TypeElement, List<VariableElement>> entry : toBeProcessed.entrySet()) {
            generateClass(entry.getKey(), entry.getValue());
        }

        return false;
    }

    private void log(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
    }

    private void generateClass(TypeElement typeElement, List<VariableElement> listOfVariables) {

        final ClassName pojoType = ClassName.get(typeElement);

        TypeSpec.Builder classBuilder = TypeSpec.
                classBuilder(typeElement.getSimpleName() + "Converter")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        classBuilder.addMethod(generateSingleRowParseMethod(typeElement, listOfVariables));

        classBuilder.addMethod(generateListParseMethod(pojoType));

        classBuilder.addMethod(generateToContentValuesMethod(typeElement, listOfVariables));

        try {
            JavaFile.builder(pojoType.packageName(), classBuilder.build())
                    .build()
                    .writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private MethodSpec generateToContentValuesMethod(TypeElement typeElement, List<VariableElement> listOfVariables) {
        final String parameterName = typeElement.getSimpleName().toString().toLowerCase();
        final ClassName contentValuesClassName = ClassName.get("android.content", "ContentValues");

        final MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("toContentValues")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(contentValuesClassName)
                .addParameter(ClassName.get(typeElement), parameterName)
                .addStatement("$T contentValues = new $T()", contentValuesClassName, contentValuesClassName);

        for (VariableElement variableElement : listOfVariables) {
            Field field = variableElement.getAnnotation(Field.class);
            methodBuilder.addStatement("contentValues.put($S, $L)", field.columnName(), parameterName + "." + variableElement.getSimpleName());
        }

        methodBuilder.addStatement("return contentValues");
        methodBuilder.addJavadoc("Converts the provided " + parameterName + " to ContentValues");
        methodBuilder.addJavadoc("\n@param " + parameterName + " to convert values from");
        methodBuilder.addJavadoc("\n@returns {@code $T} with values converted from {@code $T}", contentValuesClassName, typeElement);

        return methodBuilder.build();
    }

    private MethodSpec generateListParseMethod(ClassName pojoType) {
        // Add list parser
        TypeName listOfPojo = ParameterizedTypeName.get(LIST_TYPE, pojoType);

        return MethodSpec.methodBuilder("toList")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(listOfPojo)
                .addParameter(ClassName.get("android.database", "Cursor"), "cursor")
                .addStatement("$T list = new $T<>()", listOfPojo, ARRAY_LIST_TYPE)
                .addCode("while (cursor.moveToNext()) {\n")
                .addStatement("     list.add(toSingleRow(cursor))")
                .addCode("}\n")
                .addStatement("return list")
                .addJavadoc("Converts the {@code cursor} to {@code $T}, \nmake sure the cursor is in the correct initial position", listOfPojo)
                .addJavadoc("\n@param cursor to convert values from")
                .addJavadoc("\n@returns {@code $T} with values converted from {@code cursor}", listOfPojo)
                .build();
    }

    private MethodSpec generateSingleRowParseMethod(TypeElement typeElement, List<VariableElement> listOfVariables) {
        final MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("toSingleRow")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(ClassName.get(typeElement))
                .addParameter(ClassName.get("android.database", "Cursor"), "cursor");

        methodBuilder.addStatement("$T row = new $T()", typeElement, typeElement);

        for (VariableElement variableElement : listOfVariables) {

            checkIfTypeIsSupported(variableElement);

            if (ClassName.get(variableElement.asType()).isPrimitive()) {
                addPrimitiveType(variableElement, methodBuilder);
            } else {
                addNonPrimitiveType(variableElement, methodBuilder);
            }
        }

        methodBuilder.addStatement("return row");
        methodBuilder.addJavadoc("Converts the {@code cursor} in its current position to an $T, \nmake sure the cursor is in the correct position", typeElement);
        methodBuilder.addJavadoc("\n@param cursor to convert values from");
        methodBuilder.addJavadoc("\n@returns $T with values converted from {@code cursor}", typeElement);

        return methodBuilder.build();
    }

    private void checkIfTypeIsSupported(VariableElement variableElement) {
        final TypeName typeOfCurrentElement = ClassName.get(variableElement.asType());
        if (!typeOfCurrentElement.equals(ClassName.get(String.class)) &&
                !typeOfCurrentElement.equals(BYTE_ARRAY_TYPE) &&
                !typeOfCurrentElement.isPrimitive() &&
                !typeOfCurrentElement.isBoxedPrimitive()) {

            throwError("TinyOrm: Type " + typeOfCurrentElement + " is not supported");
        } else if ((typeOfCurrentElement.isPrimitive() || typeOfCurrentElement.isBoxedPrimitive())
                && (typeOfCurrentElement.unbox().equals(TypeName.BYTE) || typeOfCurrentElement.unbox().equals(TypeName.CHAR))) {

            throwError("TinyOrm: Type " + typeOfCurrentElement + " is not supported");
        }
    }

    private void throwError(String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message);
        throw new UnsupportedOperationException(message);
    }

    private void addNonPrimitiveType(VariableElement element, MethodSpec.Builder methodBuilder) {

        Field field = element.getAnnotation(Field.class);
        final TypeName typeOfCurrentElement = ClassName.get(element.asType());

        if (typeOfCurrentElement.equals(BYTE_ARRAY_TYPE)) {
            methodBuilder.addStatement("row.$L = cursor.getBlob(cursor.getColumnIndex($S))", element.getSimpleName(), field.columnName());
        } else {
            methodBuilder.addCode("if (!cursor.isNull(cursor.getColumnIndex($S))) {\n", field.columnName());

            if (typeOfCurrentElement.equals(ClassName.get(String.class))) {
                methodBuilder.addStatement("    row.$L = $T.valueOf(cursor.getString(cursor.getColumnIndex($S)))", element.getSimpleName(), typeOfCurrentElement, field.columnName());
            } else if (typeOfCurrentElement.unbox().equals(TypeName.BOOLEAN)) {
                methodBuilder.addStatement("    row.$L = $T.valueOf(cursor." + mapTypeToCursorGetMethod(typeOfCurrentElement.unbox()) + "(cursor.getColumnIndex($S)) == 1)", element.getSimpleName(), typeOfCurrentElement, field.columnName());
            } else {
                methodBuilder.addStatement("    row.$L = $T.valueOf(cursor." + mapTypeToCursorGetMethod(typeOfCurrentElement.unbox()) + "(cursor.getColumnIndex($S)))", element.getSimpleName(), typeOfCurrentElement, field.columnName());
            }

            methodBuilder.addCode("}\n");
        }

    }

    private void addPrimitiveType(VariableElement element, MethodSpec.Builder methodBuilder) {

        Field field = element.getAnnotation(Field.class);
        final TypeName typeName = ClassName.get(element.asType());

        if (typeName == TypeName.BOOLEAN) {
            methodBuilder.addStatement("row.$L = cursor." + mapTypeToCursorGetMethod(typeName) + "(cursor.getColumnIndex($S)) == 1", element.getSimpleName(), field.columnName());
        } else {
            methodBuilder.addStatement("row.$L = cursor." + mapTypeToCursorGetMethod(typeName) + "(cursor.getColumnIndex($S))", element.getSimpleName(), field.columnName());
        }
    }

    private String mapTypeToCursorGetMethod(TypeName typeName) {
        if (typeName == TypeName.BOOLEAN) {
            return "getInt";
        } else if (typeName == TypeName.SHORT) {
            return "getShort";
        } else if (typeName == TypeName.INT) {
            return "getInt";
        } else if (typeName == TypeName.LONG) {
            return "getLong";
        } else if (typeName == TypeName.FLOAT) {
            return "getFloat";
        } else if (typeName == TypeName.DOUBLE) {
            return "getDouble";
        }
        return "";
    }

}
