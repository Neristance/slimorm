package io.dominikschulz.slimorm;


import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class ProcessPojo {
    private final List<VariableElement> annotatedFields;
    private final List<ExecutableElement> annotatedSetters;
    private final List<ExecutableElement> annotatedGetters;
    private final List<ExecutableElement> annotatedConstructors;

    public ProcessPojo(List<VariableElement> annotatedFields,
                       List<ExecutableElement> annotatedMethods,
                       List<ExecutableElement> annotatedGetters,
                       List<ExecutableElement> annotatedConstructors) {
        this.annotatedFields = annotatedFields;
        this.annotatedSetters = annotatedMethods;
        this.annotatedGetters = annotatedGetters;
        this.annotatedConstructors = annotatedConstructors;
    }

    public List<VariableElement> getAnnotatedFields() {
        return annotatedFields;
    }

    public List<ExecutableElement> getAnnotatedSetters() {
        return annotatedSetters;
    }

    public List<ExecutableElement> getAnnotatedGetters() {
        return annotatedGetters;
    }

    public List<ExecutableElement> getAnnotatedConstructors() {
        return annotatedConstructors;
    }
}
