package com.bulletinCodeTest.CodeTest.database;

import com.bulletinCodeTest.CodeTest.models.Url;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseExceptionHandler {

    UrlDatabaseAdaptor databaseAdaptor;

    public DatabaseExceptionHandler(UrlDatabaseAdaptor databaseAdaptor) {
        this.databaseAdaptor = databaseAdaptor;
    }

    /***
     * Catch any exceptions thrown by the database, log them, and return the result
     * @param url the url we want to save
     * @return the url that we saved
     */
    public Url save(Url url) {
        try {
            return databaseAdaptor.save(url);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to create short url");
        }
    }

    /***
     * Catches any exceptions thrown by the database as we try to find the associated full url from a short url
     * @param shortUrl the short url the user has that will be used to get the full url
     * @return The url that was retrieved
     */
    public Url readByShortUrl(String shortUrl) {
        try {
            return databaseAdaptor.getByShortUrl(shortUrl);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Unable find short url");
        }
    }

    /***
     *  Catches any exception thrown by the database as we try to get all the urls available
     * @return all the urls in the database
     */
    public Optional<List<Url>> getAllUrls() {
        try {
            return Optional.of(databaseAdaptor.getAllUrls());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Optional.empty();

        }
    }

}
