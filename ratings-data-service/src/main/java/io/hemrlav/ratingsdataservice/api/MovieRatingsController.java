package io.hemrlav.ratingsdataservice.api;

import io.hemrlav.ratingsdataservice.model.Rating;
import io.hemrlav.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class MovieRatingsController {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    // ideally this should get the movies which are rated by the user with passes {userId}
    @RequestMapping("users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("550", 4), // Fight Club
                new Rating("551", 3) // The Poseidon Adventure Collection
        );
        UserRating userRating = new UserRating(userId, ratings);
        return userRating;
    }

}
