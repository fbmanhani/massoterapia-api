package br.edu.ifsp.manhani.massoterapia.messages;

import java.io.Serializable;

import br.edu.ifsp.manhani.massoterapia.exception.BusinessException;
import br.edu.ifsp.manhani.massoterapia.exception.InfraException;
import br.edu.ifsp.manhani.massoterapia.exception.ResourceNotFoundException;
import br.edu.ifsp.manhani.massoterapia.exception.SecurityValidationException;

public interface IMessageProperty extends Serializable {

    String key();

    String message();

    IMessageProperty bind(String... args);

    default BusinessException businessException() {
        return new BusinessException(this);
    }

    default BusinessException businessException(Throwable cause) {
        return new BusinessException(this, cause);
    }

    default InfraException infraException() {
        return new InfraException(this);
    }

    default InfraException infraException(Throwable cause) {
        return new InfraException(this, cause);
    }

    default ResourceNotFoundException resourceNotFoundException() {
        return new ResourceNotFoundException(this);
    }

    default ResourceNotFoundException resourceNotFoundException(Throwable cause) {
        return new ResourceNotFoundException(this, cause);
    }

    default SecurityValidationException securityValidationException() {
        return new SecurityValidationException(this);
    }

    default SecurityValidationException securityValidationException(Throwable cause) {
        return new SecurityValidationException(this, cause);
    }

}
