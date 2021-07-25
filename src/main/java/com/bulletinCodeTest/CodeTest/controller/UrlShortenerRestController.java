package com.bulletinCodeTest.CodeTest.controller;

import com.bulletinCodeTest.CodeTest.workflow.ShortUrlWorkflow;
import com.bulletinCodeTest.CodeTest.database.UrlDatabaseAdaptor;
import com.bulletinCodeTest.CodeTest.models.AllUrlResponse;
import com.bulletinCodeTest.CodeTest.models.ShortenUrlRequest;
import com.bulletinCodeTest.CodeTest.models.Url;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlShortenerRestController {
    private final ShortUrlWorkflow shortUrlWorkflow;

    public UrlShortenerRestController(UrlDatabaseAdaptor urlDatabaseAdaptor, ShortUrlWorkflow shortUrlWorkflow) {
        this.shortUrlWorkflow = shortUrlWorkflow;
    }


    /***
     * Create a new short url and return.
     * @param shortenUrlRequest url request with the full url (using a request body to avoid having to do url encoding/decoding)
     * @return the url object that has been created in JSON format, with the full url, date, and short url
     */
    @PostMapping(path="/shortUrl")
    public Url createNewShortUrl(@RequestBody ShortenUrlRequest shortenUrlRequest) {
        return shortUrlWorkflow.create(shortenUrlRequest);
    }

    /***
     * This endpoint is what is triggered by someone clicking on the short url. This will redirect them to the full url
     * @param shortenedUrl the generated portion of the short url
     * @return a Response Entity that triggers a redirect
     */
    @GetMapping(path = "/{shortenedUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortenedUrl) {
        Url url = shortUrlWorkflow.getFullUrl(shortenedUrl);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).location(URI.create(url.getFullUrl())).build();
    }

    /***
     * This endpoint will return all of the urls that have been created in our database
     * @return AllUrlResponse with either all of the urls and success, or failure and an empty list
     */
    @GetMapping(path = "/shortUrl/all")
    public AllUrlResponse getAllUrls() {
        return shortUrlWorkflow.getAllUrls();
    }
}
