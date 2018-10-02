package cheetsheets.dk.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GenericResponse<D> {

    @JsonProperty("status")
    private final String status;

    @JsonProperty("details")
    private final List<ErrorDetails> details;

    @JsonProperty("data")
    private final D data;

    public GenericResponse(Status status, List<ErrorDetails> details, D data) {
        this.status = status.getKey();
        this.details = details;
        this.data = data;
    }

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<T>(Status.SUCCESS, null, data);
    }

    public static <T> GenericResponse<T> error(List<ErrorDetails> details) {
        return new GenericResponse<T>(Status.ERROR, details, null);
    }

    private enum Status {
        SUCCESS("success"),
        ERROR("error");

        private final String key;

        Status(String key) {
            this.key = key;
        }

        private String getKey() {
            return this.key;
        }
    }

}
