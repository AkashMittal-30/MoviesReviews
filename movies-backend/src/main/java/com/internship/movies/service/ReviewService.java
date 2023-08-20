package com.internship.movies.service;

import com.internship.movies.dao.MovieRepository;
import com.internship.movies.dao.ReviewRepository;
import com.internship.movies.dao.UserRepository;
import com.internship.movies.entity.Movie;
import com.internship.movies.entity.Review;
import com.internship.movies.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private MongoTemplate mongoTemplate;
    private MovieRepository movieRepository;
    private UserRepository userRepository;
    @Autowired
    ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate,UserRepository userRepository, MovieRepository movieRepository)
    {
        this.reviewRepository=reviewRepository;
        this.mongoTemplate=mongoTemplate;
        this.userRepository=userRepository;
        this.movieRepository=movieRepository;
    }
    public Review createReview(String reviewBody, String imdbId, String mailId)
    {
        Optional<User> user=userRepository.findUserByMailId(mailId);
        User fuser;
        if(user.isEmpty()) fuser=userRepository.save(new User(mailId));
        else fuser=user.get();
        Review review = reviewRepository.insert(new Review(reviewBody,fuser));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }
    public List<Review> getAllReviews(ArrayList<String> ids)
    {
        return reviewRepository.findAllById(ids);
    }
    public Review deleteReview(String id,String movieId)
    {
        Review delReview=reviewRepository.findById(id).get();
        ObjectId nid=new ObjectId(id);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(movieId))
                .apply(new Update().pull("reviewIds", nid))
                .first();
        reviewRepository.deleteById(id);
        return delReview;
    }
}
