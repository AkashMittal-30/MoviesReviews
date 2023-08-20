// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import {getAuth} from 'firebase/auth';
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyAK5i2hmQGRxYSFMs8gJlgZGismMI_l2bM",
  authDomain: "react-authentication-59a7b.firebaseapp.com",
  projectId: "react-authentication-59a7b",
  storageBucket: "react-authentication-59a7b.appspot.com",
  messagingSenderId: "877720988838",
  appId: "1:877720988838:web:823d037b0a3c5ae6dc1867"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth=getAuth(app);
export default app;
