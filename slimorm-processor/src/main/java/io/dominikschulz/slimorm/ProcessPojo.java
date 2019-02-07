package io.dominikschulz.slimorm;


import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class ProcessPojo {
    private final List<VariableElement> annotatedFields;
    private final List<ExecutableElement> annotatedSetters;
    private final List<ExecutableElement> annotatedGetters;

    public ProcessPojo(List<VariableElement> annotatedFields, List<ExecutableElement> annotadedMethods, List<ExecutableElement> annotatedGetters) {
        this.annotatedFields = annotatedFields;
        this.annotatedSetters = annotadedMethods;
        this.annotatedGetters = annotatedGetters;
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
}
