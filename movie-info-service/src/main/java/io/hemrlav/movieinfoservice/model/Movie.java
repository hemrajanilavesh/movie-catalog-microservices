package io.hemrlav.movieinfoservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Movie {

    private String movieId;
    private String name;
    private String description;
}
