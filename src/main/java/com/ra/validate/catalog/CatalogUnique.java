package com.ra.validate.catalog;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateCatalogName.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CatalogUnique {
    String message() default "catalogName duplicate entry";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
