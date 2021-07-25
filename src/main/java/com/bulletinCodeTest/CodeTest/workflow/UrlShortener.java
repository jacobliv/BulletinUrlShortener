package com.bulletinCodeTest.CodeTest.workflow;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlShortener {

    /***
     * Take the domain out of a full url, and combine it with a random string that is 10 characters long
     * @param fullUrl url to be shortened
     * @return a shorter url with the application's host prepended
     */
    public String shorten(String fullUrl) {
        String domain =fullUrl.split("\\.")[1];
        return "http://localhost:8080/"+domain+UUID.randomUUID().toString().substring(0, 10);
    }
}
