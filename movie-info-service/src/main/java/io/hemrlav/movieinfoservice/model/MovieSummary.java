package io.hemrlav.movieinfoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieSummary {

    private String id;
    private String title;
    private String overview;

}
