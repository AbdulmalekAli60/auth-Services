import axios from "axios";
import { FormData } from "../components/Singup";
import { LogInData } from "../components/Singin";

const URL = "http://localhost:8080/api/auth";

export const signupService = (signupFormData: FormData) =>
  axios.post(`${URL}/signup`, signupFormData);

export const signinService = (signInData: LogInData) =>
  axios.post(`${URL}/login`, signInData);
