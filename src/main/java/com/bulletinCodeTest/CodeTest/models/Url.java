package com.bulletinCodeTest.CodeTest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/***
 * The main Url model that contains the full url, short url, and creation date
 */
public class Url {
    @JsonIgnore

    int id;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    LocalDateTime createdDate;
    Duration timeSinceCreation;
    String fullUrl;
    String shortUrl;

    public Url() {
    }

    public Url(String fullUrl, String shortUrl, LocalDateTime createdDate) {
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
        this.createdDate = createdDate;
        this.timeSinceCreation = Duration.between(createdDate,LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getTimeSinceCreation() {
        return "Days: " + timeSinceCreation.toDays() +
               " Hours: " + (timeSinceCreation.toHours()-(timeSinceCreation.toDays()*24)) +
               " Minutes: " + (timeSinceCreation.toMinutes()-((timeSinceCreation.toHours()-(timeSinceCreation.toDays()*24))*60));
    }

    public void setTimeSinceCreation(Duration timeSinceCreation) {
        this.timeSinceCreation = timeSinceCreation;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", date=" + createdDate +
                ", fullUrl='" + fullUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return id == url.id && Objects.equals(fullUrl, url.fullUrl) && Objects.equals(shortUrl, url.shortUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullUrl, shortUrl);
    }
}
