package com.psideris.moviesapi;

import com.psideris.moviesapi.records.Director;
import com.psideris.moviesapi.records.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {

    private List<Movie> movies = List.of(
            new Movie("1", new Director("John", "Wick"), true),
            new Movie("2", new Director("Mary", "Poppins"), false),
            new Movie("3", new Director("Jack", "Sparrow"), true),
            new Movie("4", null, false));

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movies;
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable String id) {
        return movies
                .stream()
                .filter(s -> s.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movie;
    }
}
