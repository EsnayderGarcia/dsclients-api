package com.snayder.dsclients.compatilhado;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfCnpjValidador implements ConstraintValidator<CpfCnpj, String> {
    @Override
    public boolean isValid(String documento, ConstraintValidatorContext constraintValidatorContext) {
        return cpfValidador(documento) || cnpjfValidador(documento);
    }

    private boolean cpfValidador(String documento) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);
        return cpfValidator.isValid(documento, null);
    }

    private boolean cnpjfValidador(String documento) {
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);
        return cnpjValidator.isValid(documento, null);
    }
}
