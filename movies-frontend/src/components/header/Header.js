import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faVideoSlash } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { NavLink, useNavigate } from "react-router-dom";
import { useUserAuth } from "../../context/UserAuthContext";

import React from 'react'

const Header = () => {
  const {user,logOut}=useUserAuth();
  let navigate=useNavigate();
  const loggingOut=async ()=>{
    await logOut();
    navigate("/");
  }
  console.log(user);
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container fluid>
        <Navbar.Brand>
          <NavLink to="/" style={{"color":"gold", "textDecoration": "none" }}>
            <FontAwesomeIcon icon={faVideoSlash}/> Gold
          </NavLink>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll"/>
        <Navbar.Collapse id="navbarScroll">
          <Nav 
            className="me-auto my-2 my-lg-0"
            style={{maxHeight:'100px'}}
            navbarScroll
          >
            <NavLink className="nav-link" to="/">Home</NavLink>
            <NavLink className="nav-link" to="/watchlist">Watch List</NavLink>
          </Nav>
          {user?(
            <>
              {user.displayName?user.displayName:user.email}
              <Button variant="outline-info" className="ms-2" onClick={loggingOut} >
                SignOut
              </Button>
            </>
          ):
          (
            <Button variant="outline-info"  onClick={()=>navigate("/login")}>
                Login
            </Button>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default Header