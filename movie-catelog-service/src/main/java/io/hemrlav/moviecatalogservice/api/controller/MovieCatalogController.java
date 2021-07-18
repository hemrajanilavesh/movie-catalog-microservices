package io.hemrlav.moviecatalogservice.api.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.hemrlav.moviecatalogservice.api.service.MovieInfoService;
import io.hemrlav.moviecatalogservice.api.service.UserRatingDataService;
import io.hemrlav.moviecatalogservice.model.CatalogItem;
import io.hemrlav.moviecatalogservice.model.Movie;
import io.hemrlav.moviecatalogservice.model.Rating;
import io.hemrlav.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    public MovieInfoService movieInfoService;

    @Autowired
    public UserRatingDataService userRatingDataService;

    /**
     * 1. Get the movie IDs of movies which are rated by user.
     *
     * 2. For each movie that is rated by the user, get movie information.
     *
     * 3. Return a combined result.
     *
     * @param userId
     * @return
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = userRatingDataService.getUserRating(userId);
        return userRating.getUserRatings().stream().map(rating -> movieInfoService.getCatalogItem(rating)).collect(Collectors.toList());
    }


}
