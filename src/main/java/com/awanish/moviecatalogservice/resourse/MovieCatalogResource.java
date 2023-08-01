package com.awanish.moviecatalogservice.resourse;

import com.awanish.moviecatalogservice.service.MovieInfoService;
import com.awanish.moviecatalogservice.service.UserRatingService;
import com.awanish.moviecatalogservice.dto.CatalogItem;
import com.awanish.moviecatalogservice.dto.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRatingService userRatingService;

    @Autowired
    private MovieInfoService movieInfoService;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogItem(@PathVariable("userId") String userId) {
        UserRating userRating = userRatingService.getUserRating(userId);
        List<CatalogItem> catalogItems = userRating.getUserRating().stream().map(rating -> movieInfoService.getCatalogItem(rating)).collect(Collectors.toList());
        return catalogItems;
    }
}
