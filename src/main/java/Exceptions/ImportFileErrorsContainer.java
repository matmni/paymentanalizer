package Exceptions;

import model.Payment;

import java.util.ArrayList;
import java.util.List;

public class ImportFileErrorsContainer {
    private List<ParseError> errors;
    private List<Payment> constraintErrors;


    public ImportFileErrorsContainer() {
        errors = new ArrayList<>();
        constraintErrors = new ArrayList<>();
    }

    public List<ParseError> getParseErrors() {
        return errors;
    }

    public List<Payment> getConstraintErrors() {
        return constraintErrors;
    }

    public boolean hasParseErrors() {
        return !errors.isEmpty();
    }

    public boolean hasConstraintErrors() {
        return !constraintErrors.isEmpty();
    }

}
