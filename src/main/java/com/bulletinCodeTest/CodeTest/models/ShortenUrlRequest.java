package com.bulletinCodeTest.CodeTest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Initial save request with a full url
 */
public class ShortenUrlRequest {
    @JsonProperty("fullUrl")
    String fullUrl;

    public ShortenUrlRequest() {
    }

    public ShortenUrlRequest(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
