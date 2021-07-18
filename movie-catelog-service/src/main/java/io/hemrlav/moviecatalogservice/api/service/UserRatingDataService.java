package io.hemrlav.moviecatalogservice.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.hemrlav.moviecatalogservice.model.Rating;
import io.hemrlav.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingDataService {

    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratings/users/" + userId, UserRating.class);
    }

    // fall back method for getUserRating if ratings-data-service is breaking the circuit
    private UserRating getFallBackUserRating(String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRatings(Arrays.asList(new Rating("NA", 0)));

        return userRating;
    }

}
