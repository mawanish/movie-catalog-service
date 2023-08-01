package com.awanish.moviecatalogservice.service;

import com.awanish.moviecatalogservice.dto.Rating;
import com.awanish.moviecatalogservice.dto.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public UserRating getUserRating(String userId) {
        UserRating userRating = restTemplate.getForObject("http://movie-rating-data-service/ratingsdata/user/"+ userId, UserRating.class);
        return userRating;
    }


    public UserRating getFallBackUserRating(String userId) {
        return UserRating.builder()
                .userId(userId)
                .userRating(Arrays.asList(Rating.builder()
                                .rating(0)
                                .movieId("0")
                        .build()))
                .build();
    }

}
