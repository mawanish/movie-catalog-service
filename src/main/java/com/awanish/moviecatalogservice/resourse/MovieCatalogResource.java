package com.awanish.moviecatalogservice.resourse;

import com.awanish.moviecatalogservice.dto.CatalogItem;
import com.awanish.moviecatalogservice.dto.Movie;
import com.awanish.moviecatalogservice.dto.Rating;
import com.awanish.moviecatalogservice.dto.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogItem(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://movie-rating-data-service/ratingsdata/user/"+userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);
            return CatalogItem.builder()
                    .name(movie.getTitle())
                    .desc(movie.getOverview())
                    .rating(rating.getRating())
                    .build();
        }).collect(Collectors.toList());
    }
}
