package io.dominikschulz.slimorm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate constructors to be used for creating the pojo, by default the column name is the same as the parameter name,
 * if you like to change that use {@link ColumnName}
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR})
public @interface PojoCreator {
}
