package cheetsheets.dk.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetails {

    @JsonProperty("code")
    private final int code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("field")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String field;

    public ErrorDetails(Code code, String message, String field) {
        this.code = code.code;
        this.message = message;
        this.field = field;
    }

    public ErrorDetails(Code code, String message) {
        this.code = code.code;
        this.message = message;
        this.field = null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    public enum Code {
        INTERNAL_SERVER_ERROR(500);

        private final int code;

        Code(int code) {
            this.code = code;
        }
    }

}
