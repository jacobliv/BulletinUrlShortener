package com.bulletinCodeTest.CodeTest.database;

import com.bulletinCodeTest.CodeTest.models.ShortenUrlRequest;
import com.bulletinCodeTest.CodeTest.models.Url;
import com.bulletinCodeTest.CodeTest.workflow.ShortUrlWorkflow;
import com.bulletinCodeTest.CodeTest.workflow.UrlShortener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DatabaseExceptionHandlerTest {

    @Test
    @DisplayName("Success -- create -- method is invoked with valid data -- return a url object")
    public void saveUrlsSuccess() throws Exception {
        Url url = new Url("fullUrl", "shortUrl", LocalDateTime.now());
        UrlDatabaseAdaptor database = mock(UrlDatabaseAdaptor.class);
        when(database.save(url)).thenReturn(url);

        DatabaseExceptionHandler exceptionHandler = new DatabaseExceptionHandler(database);

        assertEquals(url,exceptionHandler.save(url));
    }

    @Test
    @DisplayName("Failure -- create -- method is invoked with invalid data -- throw an exception")
    public void saveUrlsFailure() throws Exception {
        Url url = new Url("fullUrl", "shortUrl", LocalDateTime.now());
        UrlDatabaseAdaptor database = mock(UrlDatabaseAdaptor.class);
        when(database.save(url)).thenThrow(new Exception("Exception"));

        DatabaseExceptionHandler exceptionHandler = new DatabaseExceptionHandler(database);

        assertThrows(HttpServerErrorException.class,()->exceptionHandler.save(url));
    }


    @Test
    @DisplayName("Success -- readByShortUrl -- method is invoked with valid data -- return a url object")
    public void readByShortUrlSuccess() throws Exception {
        Url url = new Url("fullUrl", "shortUrl", LocalDateTime.now());
        UrlDatabaseAdaptor database = mock(UrlDatabaseAdaptor.class);
        when(database.getByShortUrl(url.getShortUrl())).thenReturn(url);

        DatabaseExceptionHandler exceptionHandler = new DatabaseExceptionHandler(database);

        assertEquals(url,exceptionHandler.readByShortUrl(url.getShortUrl()));
    }

    @Test
    @DisplayName("Failure -- readByShortUrl -- method is invoked with invalid data -- throw an exception")
    public void readByShortUrlFailure() throws Exception {
        Url url = new Url("fullUrl", "shortUrl", LocalDateTime.now());
        UrlDatabaseAdaptor database = mock(UrlDatabaseAdaptor.class);
        when(database.getByShortUrl(url.getShortUrl())).thenThrow(new Exception("Exception"));

        DatabaseExceptionHandler exceptionHandler = new DatabaseExceptionHandler(database);

        assertThrows(HttpClientErrorException.class,()->exceptionHandler.readByShortUrl(url.getShortUrl()));
    }

    @Test
    @DisplayName("Success -- getAllUrls -- method is invoked with valid data -- return a url object")
    public void getAllUrlsSuccess() throws Exception {
        List<Url> urls = new ArrayList<>();
        urls.add(new Url());
        UrlDatabaseAdaptor database = mock(UrlDatabaseAdaptor.class);
        when(database.getAllUrls()).thenReturn(urls);

        DatabaseExceptionHandler exceptionHandler = new DatabaseExceptionHandler(database);

        assertEquals(Optional.of(urls),exceptionHandler.getAllUrls());
    }

    @Test
    @DisplayName("Failure -- getAllUrls -- method is invoked with invalid data -- return optional of an empty list")
    public void getAllUrlsFailure() throws Exception {
        Url url = new Url("fullUrl", "shortUrl", LocalDateTime.now());
        UrlDatabaseAdaptor database = mock(UrlDatabaseAdaptor.class);
        when(database.getByShortUrl(url.getShortUrl())).thenThrow(new Exception("Exception"));

        DatabaseExceptionHandler exceptionHandler = new DatabaseExceptionHandler(database);

        assertEquals(Optional.of(Collections.emptyList()),exceptionHandler.getAllUrls());
    }

}