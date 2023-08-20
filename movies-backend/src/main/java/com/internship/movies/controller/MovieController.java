package com.internship.movies.controller;

import com.internship.movies.entity.Movie;
import com.internship.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MovieController {
    private MovieService movieService;
    @Autowired
    MovieController(MovieService theMovieService)
    {
        movieService=theMovieService;
    }
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
    }
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.getSingleMovie(imdbId),HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie){
        return new ResponseEntity<Movie>(movieService.updateMovie(movie),HttpStatus.OK);
    }
}
