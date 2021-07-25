package com.bulletinCodeTest.CodeTest.models;

import java.util.List;

/***
 * Response for retrieving all Urls currently in the database
 */
public class AllUrlResponse {
    UrlResponseStatus status;
    List<Url> urlList;

    public AllUrlResponse() {
    }

    public AllUrlResponse(UrlResponseStatus status, List<Url> urlList) {
        this.status = status;
        this.urlList = urlList;
    }

    public UrlResponseStatus getStatus() {
        return status;
    }

    public void setStatus(UrlResponseStatus status) {
        this.status = status;
    }

    public List<Url> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<Url> urlList) {
        this.urlList = urlList;
    }
}
