package com.awanish.moviecatalogservice.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRating {
    private String userId;
    private List<Rating> userRating;
}
