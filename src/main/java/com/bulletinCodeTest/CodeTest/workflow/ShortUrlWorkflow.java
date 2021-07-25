package com.bulletinCodeTest.CodeTest.workflow;

import com.bulletinCodeTest.CodeTest.database.DatabaseExceptionHandler;
import com.bulletinCodeTest.CodeTest.models.AllUrlResponse;
import com.bulletinCodeTest.CodeTest.models.ShortenUrlRequest;
import com.bulletinCodeTest.CodeTest.models.Url;
import com.bulletinCodeTest.CodeTest.models.UrlResponseStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ShortUrlWorkflow {
    private final DatabaseExceptionHandler databaseExceptionHandler;
    private final UrlShortener urlShortener;

    public ShortUrlWorkflow(DatabaseExceptionHandler urlDatabaseAdaptor, UrlShortener urlShortener) {
        this.databaseExceptionHandler = urlDatabaseAdaptor;
        this.urlShortener = urlShortener;
    }

    /**
     * Create a new short url, and save it to the database
     * @param shortenUrlRequest the request containing the full url
     * @return the created Url object with a short Url
     */
    public Url create(ShortenUrlRequest shortenUrlRequest) {
        String shortUrl = urlShortener.shorten(shortenUrlRequest.getFullUrl());
        Url url = new Url(shortenUrlRequest.getFullUrl(), shortUrl, LocalDateTime.now());
        return databaseExceptionHandler.save(url);
    }

    /***
     * Retrieve the full url by using the short url
     * @param shortUrl short url the consumer is attempting to navigate to
     * @return the full url associated with the short url
     */
    public Url getFullUrl(String shortUrl){
        return databaseExceptionHandler.readByShortUrl(shortUrl);
    }

    /***
     * Get all the urls in the database.
     * @return If the optional is empty, return empty list with a failure, otherwise return the list with a Success
     */
    public AllUrlResponse getAllUrls() {
        Optional<List<Url>> allUrlOptional = databaseExceptionHandler.getAllUrls();

        return allUrlOptional.map(urls -> new AllUrlResponse(UrlResponseStatus.SUCCESS, urls))
                .orElseGet(() -> new AllUrlResponse(UrlResponseStatus.FAILURE, Collections.emptyList()));

    }
}
