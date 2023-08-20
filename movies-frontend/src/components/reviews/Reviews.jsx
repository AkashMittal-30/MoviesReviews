import { useEffect, useRef, useState } from "react";
import api from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';
import { Container, Row, Col, Card } from "react-bootstrap";
import ReviewForm from '../reviewForm/ReviewForm';
import React from 'react';
import { useUserAuth } from "../../context/UserAuthContext";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import "./Reviews.css";

const Reviews = () => {
  const reviewText = useRef();
  const editReviewText = useRef();
  const movieId = useParams().movieId;
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState([]);
  const {user} = useUserAuth();
  const [isOpen, setIsOpen] = useState(false);
  const [rid,setRid]=useState();
  const [rbody,setRbody]=useState();

  const togglePopup = () => {
    setIsOpen(!isOpen);
  };

  const getMovieData = async () => {
    try {
      const response = await api.get(`/api/v1/movies/${movieId}`);
      const singleMovie = response.data;
      setMovie(singleMovie);
      getReviews();
    } catch (err) {
      console.log(err);
    }
  }

  const getReviews = async () => {
    try {
      const response = await api.get(`/api/v1/reviews/${movieId}`);
      let updatedReviews = response.data;
      updatedReviews = updatedReviews.reverse();
      console.log(updatedReviews);
      setReviews(updatedReviews);
    } catch (err) {
      console.log(err);
    }
  }

  const addReview = async (e) => {
    e.preventDefault();
    try {
      console.log(user.email);
      await api.post("/api/v1/reviews", { reviewBody: reviewText.current.value, imdbId: movieId, mailId: user.email});
      reviewText.current.value="";
      getReviews();
    } catch (err) {
      console.log(err);
    }
  }
  const editReview = async (e) => {
    e.preventDefault();
    try {
      console.log(user.email);
      await deleteReview(rid);
      await api.post("/api/v1/reviews", { reviewBody: editReviewText.current.value, imdbId: movieId, mailId: user.email});
      editReviewText.current.value="";
      getReviews();
      togglePopup();
    } catch (err) {
      console.log(err);
    }
  }
  const deleteReview = async (reviewId) => {
    console.log(reviewId);
    try {
      await api.delete("/api/v1/reviews", {
        data: { reviewId: reviewId, imdbId: movieId }
      });
      getReviews();
    } catch (err) {
    }
  };
  useEffect(() => {
    getMovieData(movieId);
  },[movieId]);

  return (
    <Container>
      {
        isOpen && (
        <div className="popup-overlay">
          <div className="popup-content">
            <span className="close-button" onClick={()=>togglePopup()}>
              &times;
            </span>
            <ReviewForm handleSubmit={editReview} reviewText={editReviewText} labelText="Edit Review?" defaultValue={rbody} />
          </div>
        </div>
      )}
      <Row>
        <Col><h3 className="mt-2">Reviews</h3></Col>
      </Row>
      <Row className="mt-2">
        <Col xs={12} md={6}>
          <img src={movie?.poster} alt="" style={{ maxWidth: "100%", height: "auto" }} />
        </Col>
        <Col xs={12} md={6}>
          <>
            <Row>
              <Col>
                <ReviewForm handleSubmit={addReview} reviewText={reviewText} labelText="Write a Review?" defaultValue="" />
              </Col>
            </Row>
            <Row>
              <Col>
                <hr />
              </Col>
            </Row>
          </>
          {
            reviews?.map((r, index) => {
              return (
                <div key={index}>
                  <Row>
                    <Col>
                      <Card.Text>
                        <div className="review-card">
                          <div className="review-text">
                            {r.body.split("\n").map((line, i) => (
                              <span key={i}>
                                {line}
                                <br />
                              </span>
                            ))}
                          </div>
                          <div className="review-buttons">
                            {
                              (r.client.mailId===user?.email)?
                              (
                                <>
                                  <DeleteIcon 
                                    className="mt-3 delete-icon r-icon"
                                    onClick={()=>deleteReview(r.id)}
                                  />
                                  <EditIcon 
                                    className="mt-3 ms-2 edit-icon r-icon"
                                    onClick={()=>{
                                      setRid(r.id);
                                      setRbody(r.body);
                                      togglePopup();
                                      }}
                                  />
                                </>
                              ):
                              ("")
                            }
                          </div>
                        </div>
                      </Card.Text>
                    </Col>
                  </Row>
                  <Row>
                    <Col>
                      <hr />
                    </Col>
                  </Row>
                </div>
              )
            })
          }
        </Col>
      </Row>
      <Row>
        <Col>
          <hr />
        </Col>
      </Row>
    </Container>
  )
}

export default Reviews;