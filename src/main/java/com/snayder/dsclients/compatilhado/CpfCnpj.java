package com.snayder.dsclients.compatilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CpfCnpjValidador.class})
public @interface CpfCnpj {
    String message() default "CPF/CNPJ Inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
