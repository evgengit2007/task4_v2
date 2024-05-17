package ru.task4v2.logging;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Inherited
public @interface LogTransformation {
    String logName() default "C:/temp/log/log_transformation_default.log";
}
