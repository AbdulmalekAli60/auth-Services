import axios from "axios";
import { formData } from "../components/Singup";
import { LogInData } from "../components/Singin";

const URL = "http://localhost:8080/api/auth";

export const signupService = (signupFormData: formData) =>
  axios.post(`${URL}/signup`, signupFormData);

export const signinService = (signInData: LogInData) =>
  axios.post(`${URL}/login`, signInData);
