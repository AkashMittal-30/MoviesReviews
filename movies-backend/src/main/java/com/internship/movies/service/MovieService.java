package com.internship.movies.service;

import com.internship.movies.dao.MovieRepository;
import com.internship.movies.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    @Autowired
    MovieService(MovieRepository theMovieRepository)
    {
        movieRepository=theMovieRepository;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public Optional<Movie> getSingleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }
    public Movie updateMovie(Movie movie)
    {
        return movieRepository.save(movie);
    }
}
