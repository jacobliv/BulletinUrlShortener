package com.bulletinCodeTest.CodeTest.database;

import com.bulletinCodeTest.CodeTest.models.Url;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlDatabaseAdaptor {
    private final JdbcTemplate database;

    public UrlDatabaseAdaptor(JdbcTemplate database) {
        this.database = database;
    }

    /***
     * Saves a Url Object
     * @param url the url object with the short url already ceated
     * @return the url object to confirm it has been created
     * @throws Exception If any exception is thrown by the database we want to be able to catch it in the Exception Handler
     */
    public Url save(Url url) throws Exception {
        database.execute("CREATE TABLE IF NOT EXISTS " +
                             "Url(id Number PRIMARY KEY AUTOINCREMENT,short_url VARCHAR(100),full_url VARCHAR(100), created_date VARCHAR(100) )");
        database.execute(String.format("INSERT INTO Url (short_url, full_url, created_date) " +
                                                "VALUES ('%s'     , '%s'    ,'%s')",
                url.getShortUrl(),url.getFullUrl(),url.getCreatedDate().toString() ));
        return url;
    }

    /***
     * Retrieve a url object by searching by short url
     * @param shortUrl the short URL that the consumer uses instead of a full url
     * @return the URL object associated with the short url
     * @throws Exception If any exception is thrown by the database, we want to be able to catch it in the Exception Handler
     */
    public Url getByShortUrl(String shortUrl) throws Exception {
        List<String> responses = new ArrayList<>();
        List<Url> urls = new ArrayList<>();
        database.query(String.format("SELECT short_url, full_url, created_date FROM Url WHERE short_url=%s", "'http://localhost:8080/" + shortUrl + "'"),
                (ResultSet resultSet,int rowNum) -> {

                    responses.add(resultSet.getString("full_url"));
                    responses.add(resultSet.getString("short_url"));
                    responses.add(resultSet.getString("created_date"));
                    return null;
                });
        for (int i = 0; i < responses.size(); i+=3) {
            urls.add(new Url(responses.get(i),
                             responses.get(i+1),
                             LocalDateTime.parse(responses.get(i+2))));
        }

        if(urls.size() ==0 ) throw new EmptyResultDataAccessException("No full url found",0);
        return urls.get(0);
    }

    /***
     * Retrieve all urls from the database
     * @return a list of Url objects that includes all data for the url that has been stored
     * @throws ParseException if we can't parse the date properly (since SQLite doesn't support dates), we throw a parse exception
     */
    public List<Url> getAllUrls() throws ParseException {
        List<String> responses = new ArrayList<>();
        List<Url> urls = new ArrayList<>();
        database.query("SELECT short_url, full_url, created_date FROM Url",
                (ResultSet resultSet,int rowNum) -> {
                    responses.add(resultSet.getString("full_url"));
                    responses.add(resultSet.getString("short_url"));
                    responses.add(resultSet.getString("created_date"));
                    return null;
                });
        for (int i = 0; i < responses.size(); i+=3) {
            urls.add(new Url(responses.get(i),
                    responses.get(i+1),
                    LocalDateTime.parse(responses.get(i+2))));
        }


        return urls;
    }

}
