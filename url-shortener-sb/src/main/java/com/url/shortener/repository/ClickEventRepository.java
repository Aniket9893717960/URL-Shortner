package com.url.shortener.repository;

import com.url.shortener.models.ClickEvent;
import com.url.shortener.models.UrlMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {
    Page<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping urlMapping,
                                                         LocalDateTime start,
                                                         LocalDateTime end,
                                                         Pageable pageable);

    List<ClickEvent> findAllByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappings,
                                                              LocalDateTime start,
                                                              LocalDateTime end);
}