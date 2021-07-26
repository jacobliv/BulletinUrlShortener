package com.bulletinCodeTest.CodeTest.workflow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UrlShortenerTest {

    @Test
    @DisplayName("Success -- shorten -- when passed a url with a domain -- expect a short url with the domain included")
    public void saveUrlsSuccess() {
        String fullUrl = "www.google.com";
        String domain = "google";
        UrlShortener urlShortener = new UrlShortener();

        assertTrue(urlShortener.shorten(fullUrl).contains(domain));
    }

}