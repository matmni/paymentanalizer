package Exceptions;

import java.util.ArrayList;
import java.util.List;

public class ImportFileErrorsContainer {
    private List<ParseError> errors;


    public ImportFileErrorsContainer() {
        errors = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public List<ParseError> getMessages() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }


}
