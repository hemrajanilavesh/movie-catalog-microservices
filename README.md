# Micro-services to get Movie Catalog rated by a User

![movie-catalog-diagram](https://user-images.githubusercontent.com/11674305/126149937-c4dab9e3-b9bd-4ca0-9032-6b961f2d4aa3.PNG)


## [Discovery Server](https://github.com/hemrajanilavesh/movie-catalog-microservices/tree/main/discovery-server)
  - Eureka Server which can register microservices.

## [Ratings Data Service](https://github.com/hemrajanilavesh/movie-catalog-microservices/tree/main/ratings-data-service)
  - Micro service which returns movies rated by a User
  - Registers itself to `Eureka Server` as `ratings-data-service`

## [Movie Info Service](https://github.com/hemrajanilavesh/movie-catalog-microservices/tree/main/movie-info-service)
  - Micro service which returns movie information based on movie id
  - Registers itself to `Eureka Server` as `movie-info-service`
  - Gets movie information from `https://www.themoviedb.org`

## [Movie Catalog Service](https://github.com/hemrajanilavesh/movie-catalog-microservices/tree/main/movie-catelog-service)
  - Registers itself to `Eureka Server` as `movie-catelog-service`
  - It combines the results of `ratings-data-service` and `movie-info-service` to return a movie catalog
  - Implements circuit breaker using `Hystrix` to call Fallback methods in case `ratings-data-service` or `movie-info-service` are unresponsive.
