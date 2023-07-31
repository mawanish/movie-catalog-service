package com.awanish.moviecatalogservice.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Rating {
    private String movieId;
    private int rating;
}
