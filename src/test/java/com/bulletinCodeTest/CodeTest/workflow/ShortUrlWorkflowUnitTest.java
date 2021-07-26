package com.bulletinCodeTest.CodeTest.workflow;

import com.bulletinCodeTest.CodeTest.database.DatabaseExceptionHandler;
import com.bulletinCodeTest.CodeTest.models.AllUrlResponse;
import com.bulletinCodeTest.CodeTest.models.ShortenUrlRequest;
import com.bulletinCodeTest.CodeTest.models.Url;
import com.bulletinCodeTest.CodeTest.models.UrlResponseStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShortUrlWorkflowUnitTest {

    @Test
    @DisplayName("Success -- getAllUrls -- method is invoked with valid data -- expect a valid response")
    public void getAllUrlsSuccess() {
        List<Url> urlList = new ArrayList<>();
        urlList.add(new Url());
        AllUrlResponse response = new AllUrlResponse(UrlResponseStatus.SUCCESS,urlList);

        DatabaseExceptionHandler exceptionHandler = mock(DatabaseExceptionHandler.class);
        when(exceptionHandler.getAllUrls()).thenReturn(Optional.of(urlList));

        UrlShortener urlShortener = mock(UrlShortener.class);

        ShortUrlWorkflow workflow = new ShortUrlWorkflow(exceptionHandler,urlShortener);

        assertEquals(response,workflow.getAllUrls());
    }

    @Test
    @DisplayName("Failure -- getAllUrls -- method is invoked with invalid data -- expect a failure")
    public void getAllUrlsFailure() {
        AllUrlResponse response = new AllUrlResponse(UrlResponseStatus.FAILURE, Collections.emptyList());

        DatabaseExceptionHandler exceptionHandler = mock(DatabaseExceptionHandler.class);
        when(exceptionHandler.getAllUrls()).thenReturn(Optional.empty());

        UrlShortener urlShortener = mock(UrlShortener.class);

        ShortUrlWorkflow workflow = new ShortUrlWorkflow(exceptionHandler,urlShortener);

        assertEquals(response,workflow.getAllUrls());
    }

    @Test
    @DisplayName("Success -- create -- method is invoked with valid data -- expect a url response")
    public void saveUrlsSuccess() {
        Url url = new Url("fullUrl", "shortUrl", LocalDateTime.now());
        ShortenUrlRequest request = new ShortenUrlRequest(url.getFullUrl());

        DatabaseExceptionHandler exceptionHandler = mock(DatabaseExceptionHandler.class);
        when(exceptionHandler.save(url)).thenReturn(url);

        UrlShortener urlShortener = mock(UrlShortener.class);
        when(urlShortener.shorten(url.getFullUrl())).thenReturn(url.getShortUrl());

        ShortUrlWorkflow workflow = new ShortUrlWorkflow(exceptionHandler,urlShortener);

        assertEquals(url,workflow.create(request));
    }

    @Test
    @DisplayName("Success -- getFullUrl -- method is invoked with valid data -- expect a url response")
    public void getFullUrlSuccess() {
        Url url = new Url("fullUrl", "shortUrl", LocalDateTime.now());

        DatabaseExceptionHandler exceptionHandler = mock(DatabaseExceptionHandler.class);
        when(exceptionHandler.readByShortUrl(url.getShortUrl())).thenReturn(url);

        UrlShortener urlShortener = mock(UrlShortener.class);

        ShortUrlWorkflow workflow = new ShortUrlWorkflow(exceptionHandler,urlShortener);

        assertEquals(url,workflow.getFullUrl(url.getShortUrl()));
    }

}