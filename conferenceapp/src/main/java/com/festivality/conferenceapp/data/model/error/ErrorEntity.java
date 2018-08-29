package com.festivality.conferenceapp.data.model.error;


/**
 * Entity for handling error
 */

public class ErrorEntity {

    public static final int HTTP_ERROR_CODE_UNAUTHORIZED = 401;

    public static final String OOPS = "Unexpected error while requesting API";
    public static final String NETWORK_UNAVAILABLE = "Network problem!";
    public static final String ERROR_UNAUTHORIZED = "Error! Please re-login!";

    private String message = "";
    private int httpCode = 0;


    public ErrorEntity(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorEntity getError(int code, String reason) {
        if (reason != null) {
            return new ErrorEntity(reason, code);
        } else {
            return new ErrorEntity(OOPS, code);
        }
    }

    public static ErrorEntity getErrorOops() {
        return new ErrorEntity(OOPS, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ErrorEntity entity = (ErrorEntity)obj;
        return this.httpCode == entity.httpCode && this.message.equals(entity.getMessage());
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "message='" + message + '\'' +
                ", httpCode=" + httpCode +
                '}';
    }
}
