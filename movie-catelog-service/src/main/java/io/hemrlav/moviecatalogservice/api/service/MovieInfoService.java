package io.hemrlav.moviecatalogservice.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.hemrlav.moviecatalogservice.model.CatalogItem;
import io.hemrlav.moviecatalogservice.model.Movie;
import io.hemrlav.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    // fall back method for getCatalogItem if movie-info-service is breaking the circuit
    private CatalogItem getFallBackCatalogItem(Rating rating) {
        return new CatalogItem("NA", "NA", 0);
    }
}
