package com.festivality.conferenceapp.data.model.device;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class DeviceID extends RealmObject {
    @SerializedName("response")
    private DeciveIDResponse response;
    @SerializedName("message")
    private String message;
    @SerializedName("host")
    private String host;
    @SerializedName("userAuthenticated")
    private String userAuthenticated;
    @SerializedName("responseSize")
    private String responseSize;
    @SerializedName("status")
    private String status;
    @SerializedName("path")
    private String path;
    @SerializedName("method")
    private String method;
    @SerializedName("apiAuthenticated")
    private String apiAuthenticated;

    public DeciveIDResponse getResponse ()
    {
        return response;
    }

    public void setResponse (DeciveIDResponse response)
    {
        this.response = response;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getHost ()
    {
        return host;
    }

    public void setHost (String host)
    {
        this.host = host;
    }

    public String getUserAuthenticated ()
    {
        return userAuthenticated;
    }

    public void setUserAuthenticated (String userAuthenticated)
    {
        this.userAuthenticated = userAuthenticated;
    }

    public String getResponseSize ()
    {
        return responseSize;
    }

    public void setResponseSize (String responseSize)
    {
        this.responseSize = responseSize;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getPath ()
    {
        return path;
    }

    public void setPath (String path)
    {
        this.path = path;
    }

    public String getMethod ()
    {
        return method;
    }

    public void setMethod (String method)
    {
        this.method = method;
    }

    public String getApiAuthenticated ()
    {
        return apiAuthenticated;
    }

    public void setApiAuthenticated (String apiAuthenticated)
    {
        this.apiAuthenticated = apiAuthenticated;
    }

    @Override
    public String toString() {
        return "DeviceID{" +
                "response=" + response +
                ", message='" + message + '\'' +
                ", host='" + host + '\'' +
                ", userAuthenticated='" + userAuthenticated + '\'' +
                ", responseSize='" + responseSize + '\'' +
                ", status='" + status + '\'' +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", apiAuthenticated='" + apiAuthenticated + '\'' +
                '}';
    }
}
