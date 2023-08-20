package com.internship.movies.controller;

import com.internship.movies.entity.Review;
import com.internship.movies.service.MovieService;
import com.internship.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
    private ReviewService reviewService;
    private MovieService movieService;
    @Autowired
    ReviewController(ReviewService reviewService, MovieService movieService)
    {
        this.reviewService=reviewService;
        this.movieService=movieService;
    }
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload)
    {
        return new ResponseEntity<>(reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId"),payload.get("mailId")), HttpStatus.CREATED);
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<List<Review>> getReview(@PathVariable String movieId)
    {
        ArrayList<String> ids=new ArrayList<>();
        movieService.getSingleMovie(movieId).get().getReviewIds().forEach(review-> ids.add(review.getId().toString()));
        return new ResponseEntity<>(reviewService.getAllReviews(ids),HttpStatus.OK);
    }
    @DeleteMapping()
    public ResponseEntity<Review> deleteReview(@RequestBody Map<String,String> payload)
    {
        return new ResponseEntity<>(reviewService.deleteReview(payload.get("reviewId"),payload.get("imdbId")),HttpStatus.OK);
    }
}
