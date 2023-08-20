import React from 'react';
import { Form, Button } from 'react-bootstrap';

const ReviewForm = ({ handleSubmit, reviewText, labelText, defaultValue }) => {
  const handleKeyDown = (event) => {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      handleSubmit(event);
    }
  };
  console.log(defaultValue);
  return (
    <Form>
      <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
        <Form.Label>{labelText}</Form.Label>
        <Form.Control
          ref={reviewText}
          as="textarea"
          rows={3}
          defaultValue={defaultValue}
          placeholder="Write..."
          onKeyDown={handleKeyDown}
        />
      </Form.Group>
      <Button variant="outline-info" onClick={handleSubmit}>
        Submit
      </Button>
    </Form>
  );
};

export default ReviewForm;
