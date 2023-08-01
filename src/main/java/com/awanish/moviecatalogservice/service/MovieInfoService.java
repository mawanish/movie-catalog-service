package com.awanish.moviecatalogservice.service;

import com.awanish.moviecatalogservice.dto.CatalogItem;
import com.awanish.moviecatalogservice.dto.Movie;
import com.awanish.moviecatalogservice.dto.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);
        return CatalogItem.builder()
                .name(movie.getTitle())
                .desc(movie.getOverview())
                .rating(rating.getRating())
                .build();
    }

    public CatalogItem getFallBackCatalogItem(Rating rating) {
        return CatalogItem.builder()
                .name("Movie not found")
                .desc("")
                .rating(rating.getRating())
                .build();
    }


}
