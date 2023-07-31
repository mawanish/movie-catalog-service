package com.awanish.moviecatalogservice.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CatalogItem {
    private String name;
    private String desc;
    private int rating;
}
