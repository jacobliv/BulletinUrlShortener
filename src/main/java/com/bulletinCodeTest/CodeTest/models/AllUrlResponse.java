package com.bulletinCodeTest.CodeTest.models;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllUrlResponse response = (AllUrlResponse) o;
        return status == response.status && Objects.equals(urlList, response.urlList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, urlList);
    }
}
