package com.healthtrack.calculator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation for recording possible flaws in code
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Warning {

    enum Type{
        DEBUG,
        REVIEW_NEEDED,
        ADDITIONAL_FEATURE,
        DELETE_IN_FUTURE
    }

    Type value();
}
