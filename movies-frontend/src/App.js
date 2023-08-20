import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import Trailer from './components/trailer/Trailer';
import Reviews from './components/reviews/Reviews';
import Login from './components/authentication/Login';
import Signup from './components/authentication/Signup';
import { UserAuthContextProvider } from "./context/UserAuthContext";
function App() 
{
  const [movies,setMovies] = useState([]);
  const getMovies = async ()=>{
    try
    {
      console.log('fetching');
      const response= await api.get("/api/v1/movies");
      console.log(response.data);
      setMovies(response.data);
    }
    catch(err)
    {
      console.log(err);
    }
  }
  useEffect(()=>{
    getMovies();
  },[]);
  return (
    <div className="App">
      <UserAuthContextProvider>
        <Header/>
        <Routes>
          <Route path="/" element={<Layout/>}>
            <Route path='/' element={<Home movies={movies}/>}/>
            <Route path="/Trailer/:ytTrailerId" element={<Trailer/>}/>
            <Route path="/Reviews/:movieId" element={<Reviews/>}/>
            <Route path='/login' element={<Login/>} />
            <Route path='/signup' element={<Signup/>} />
          </Route>
        </Routes>
      </UserAuthContextProvider>
    </div>
  );
}

export default App;
