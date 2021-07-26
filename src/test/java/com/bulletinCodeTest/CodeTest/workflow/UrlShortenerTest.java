package com.bulletinCodeTest.CodeTest.workflow;

import com.bulletinCodeTest.CodeTest.database.DatabaseExceptionHandler;
import com.bulletinCodeTest.CodeTest.models.ShortenUrlRequest;
import com.bulletinCodeTest.CodeTest.models.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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