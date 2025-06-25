package com.url.shortener.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "click_date") // Explicit column mapping
    private LocalDateTime clickDate;

    @ManyToOne
    @JoinColumn(name="url_mapping_id")
    private UrlMapping urlMapping;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getClickDate() {
        return clickDate;
    }

    public void setClickDate(LocalDateTime clickDate) {
        this.clickDate = clickDate;
    }

    public UrlMapping getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(UrlMapping urlMapping) {
        this.urlMapping = urlMapping;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClickEvent that = (ClickEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(clickDate, that.clickDate) && Objects.equals(urlMapping, that.urlMapping);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clickDate, urlMapping);
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "id=" + id +
                ", clickDate=" + clickDate +
                '}';
    }

}
