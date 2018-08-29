package com.festivality.conferenceapp.data.model.error;

import java.util.List;



public class ErrorResponse {
    private String message;
    private String documentation_url;
    private List<ErrorsModel> errors;

    @Override
    public String toString() {
        return "GitHubErrorResponse{" +
                "message='" + message + '\'' +
                ", documentation_url='" + documentation_url + '\'' +
                ", errors=" + errors +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation_url() {
        return documentation_url;
    }

    public void setDocumentation_url(String documentation_url) {
        this.documentation_url = documentation_url;
    }
}