package com.url.shortener.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

public class UrlMappingDTO {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private int clickCount;
    private LocalDateTime  createdDate;
    private String username;

    // No-args constructor (required for instantiation)
    public UrlMappingDTO() {
    }

    public UrlMappingDTO(Long id, String originalUrl, String shortUrl, int clickCount, LocalDateTime createdDate, String username) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.clickCount = clickCount;
        this.createdDate = createdDate;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UrlMappingDTO that = (UrlMappingDTO) o;
        return clickCount == that.clickCount && Objects.equals(id, that.id) && Objects.equals(originalUrl, that.originalUrl) && Objects.equals(shortUrl, that.shortUrl) && Objects.equals(createdDate, that.createdDate) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalUrl, shortUrl, clickCount, createdDate, username);
    }

    @Override
    public String toString() {
        return "UrlMappingDTO{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", clickCount=" + clickCount +
                ", createdDate=" + createdDate +
                ", username='" + username + '\'' +
                '}';
    }
}
