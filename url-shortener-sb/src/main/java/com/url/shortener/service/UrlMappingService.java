package com.url.shortener.service;

import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.models.ClickEvent;
import com.url.shortener.models.UrlMapping;
import com.url.shortener.models.User;
import com.url.shortener.repository.ClickEventRepository;
import com.url.shortener.repository.UrlMappingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final ClickEventRepository clickEventRepository;

    public UrlMappingService(UrlMappingRepository urlMappingRepository,
                             ClickEventRepository clickEventRepository) {
        this.urlMappingRepository = urlMappingRepository;
        this.clickEventRepository = clickEventRepository;
    }

    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());

        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
        return convertToDto(savedUrlMapping);
    }

    public Page<UrlMappingDTO> getUrlsByUser(User user, Pageable pageable) {
        // Updated repository method call
        Page<UrlMapping> urlPage = urlMappingRepository.findByUser(user, pageable);
        List<UrlMappingDTO> dtos = urlPage.getContent()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, urlPage.getTotalElements());
    }

    public Page<ClickEventDTO> getClickEventsByDate(String shortUrl,
                                                    LocalDateTime start,
                                                    LocalDateTime end,
                                                    Pageable pageable) {
        return urlMappingRepository.findByShortUrl(shortUrl)
                .map(urlMapping -> {
                    // Updated repository method call
                    Page<ClickEvent> clickPage = clickEventRepository.findByUrlMappingAndClickDateBetween(
                            urlMapping, start, end, pageable);

                    List<ClickEventDTO> dtos = clickPage.getContent()
                            .stream()
                            .map(click -> {
                                ClickEventDTO dto = new ClickEventDTO();
                                dto.setClickDate(click.getClickDate());
                                dto.setCount(1L);
                                return dto;
                            })
                            .collect(Collectors.toList());

                    return new PageImpl<>(dtos, pageable, clickPage.getTotalElements());
                })
                .orElse(new PageImpl<>(Collections.emptyList(), pageable, 0));
    }

    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user,
                                                            LocalDateTime start,
                                                            LocalDateTime end) {
        // Updated repository method call
        List<UrlMapping> urlMappings = urlMappingRepository.findAllByUser(user);
        List<ClickEvent> clickEvents = clickEventRepository.findAllByUrlMappingInAndClickDateBetween(
                urlMappings, start, end.plusDays(1));

        return clickEvents.stream()
                .collect(Collectors.groupingBy(
                        click -> click.getClickDate().toLocalDate(),
                        Collectors.counting()
                ));
    }

    private UrlMappingDTO convertToDto(UrlMapping urlMapping) {
        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());
        urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());

        if (urlMapping.getUser() != null) {
            urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
        }
        return urlMappingDTO;
    }

    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }

    public UrlMapping getOriginalUrl(String shortUrl) {
        Optional<UrlMapping> optionalUrlMapping = urlMappingRepository.findByShortUrl(shortUrl);

        UrlMapping urlMapping = optionalUrlMapping
                .orElseThrow(() -> new NoSuchElementException("Short URL not found: " + shortUrl));

        // Increase click count
        urlMapping.setClickCount(urlMapping.getClickCount() + 1);
        urlMappingRepository.save(urlMapping);

        // Record Click Event
        ClickEvent clickEvent = new ClickEvent();
        clickEvent.setClickDate(LocalDateTime.now());
        clickEvent.setUrlMapping(urlMapping);
        clickEventRepository.save(clickEvent);

        return urlMapping;
    }
}