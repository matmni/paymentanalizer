package Exceptions;

public class ParseError {
    private String message;
    private String row;

    public ParseError(String message, String row) {
        this.message = message;
        this.row = row;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
}