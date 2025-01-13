import { Link, useNavigate } from "react-router-dom";
import Input from "./Input";
import Header from "./Header";
import { useState } from "react";
import { signupService } from "../services/AuthServices";
// import { AxiosError } from "axios";
import {
  // FormData
  FormErrors,
  validateField,
  validateForm,
  isFormValid,
} from "../services/formValidation";
import Alert from "./Alert";

export interface FormData {
  name: string;
  userName: string;
  email: string;
  password: string;
}

export default function Singup() {
  const navigator = useNavigate();

  const [formData, setFormData] = useState<FormData>({
    name: "",
    userName: "",
    email: "",
    password: "",
  });

  const [Errors, setErrors] = useState<FormErrors>({
    name: "",
    userName: "",
    email: "",
    password: "",
  });

  // Event handlers
  function handleChaneg(e: React.ChangeEvent<HTMLInputElement>) {
    // 1- take the name of the input and the value, 2- set each name to its corresponding value
    const { name, value } = e.target; // give us the input that has chnaged
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    const error = validateField(name, value);
    setErrors((prev) => ({
      ...prev,
      [name]: error,
    }));
  }

  const isFormValid = () => {
    // Check if all fields are filled and there are no errors
    return (
      Object.values(formData).every((value) => value.trim() !== "") &&
      Object.values(Errors).every((error) => error === "")
    );
  };

  async function handleFormSubmit(e: React.FormEvent): Promise<void> {
    e.preventDefault();
    // api request is here
    const newErrors = validateForm(formData);
    setErrors(newErrors);

    if (!Object.values(newErrors).some((error) => error !== "")) {
      try {
        const response = await signupService(formData);
        sessionStorage.setItem("token", response.data.accessToken);
        navigator("/");
      } catch (error: any) {
        if (error.response) {
          const fieldName = error.response.data.field || "userName";
          setErrors((prev) => ({
            ...prev,
            [fieldName]: error.response.data.message,
          }));
        }
      }
    }
  }
  // Evenet Handlers

  return (
    <div>
      <Header pageTitle="Sign up Page" signType="Sign in" signRoute="/signin" />
      <main className="flex-1  flex items-center justify-center py-8">
        {/* <h1 className="text-center">Sign up</h1> */}

        {/* form container */}
        <div className="w-full max-w-md p-8  bg-gray-400 rounded-3xl opacity-80">
          {/* form */}
          <form className="flex flex-col gap-2">
            <Input
              lable="Name"
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChaneg}
              error={Errors.name}
            />
            <Input
              lable="User Name"
              type="text"
              name="userName"
              value={formData.userName}
              onChange={handleChaneg}
              error={Errors.userName}
            />
            <Input
              lable="Email"
              type="text"
              name="email"
              value={formData.email}
              onChange={handleChaneg}
              error={Errors.email}
            />
            <Input
              lable="Password"
              type="text"
              name="password"
              value={formData.password}
              onChange={handleChaneg}
              error={Errors.password}
            />
          </form>
          {/* form */}

          <button
            onClick={(e) => handleFormSubmit(e)}
            disabled={!isFormValid()}
            className="w-full mt-7  bg-blue-700 text-white rounded-3xl h-9"
          >
            Join
          </button>

          <div className="w-fit  flex gap-1 p-1 mt-3">
            <span>Have an account?</span>
            <Link className="text-blue-900" to={"/signin"}>
              Sign in!
            </Link>
          </div>
        </div>

        {/* form container */}
      </main>
    </div>
  );
}
