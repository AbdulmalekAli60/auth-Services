import { Link, useNavigate } from "react-router-dom";
import Header from "./Header";
import Input from "./Input";
import { useState } from "react";
import { signinService } from "../services/AuthServices";
import { useAlert } from "../contexts/AlertContext";
import {
  LogInData,
  FormErrors,
  validateField,
  validateLoginForm,
  isFormValid,
} from "../services/formValidation";

export default function Signin() {
  const navigator = useNavigate();
  const { showAlert } = useAlert();

  const [loginData, setLoginData] = useState<LogInData>({
    userName: "",
    password: "",
  });

  const [errors, setErrors] = useState<FormErrors>({
    userName: "",
    password: "",
  });

  // Event handlers
  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target;
    setLoginData((prev) => ({
      ...prev,
      [name]: value,
    }));

    // Validate field as user types (true flag for login validation)
    const error = validateField(name, value, true);
    setErrors((prev) => ({
      ...prev,
      [name]: error,
    }));
  }

  async function handleFormSubmit(e: React.FormEvent): Promise<void> {
    e.preventDefault();

    // Validate entire form before submission
    const newErrors = validateLoginForm(loginData);
    setErrors(newErrors);

    // Check if there are any errors
    if (!Object.values(newErrors).some((error) => error !== "")) {
      try {
        const response = await signinService(loginData);
        sessionStorage.setItem("token", response.data.accessToken);
        showAlert("Logged In!", "bg-green-700");
        navigator("/");
      } catch (error: any) {
        console.error(error);
        if (error.response?.data?.field) {
          // Handle specific field errors from the server
          setErrors((prev) => ({
            ...prev,
            [error.response.data.field]: error.response.data.message,
          }));
        }
        showAlert("Login failed!", "bg-red-700");
      }
    }
  }

  return (
    <div>
      <Header pageTitle="Sign in Page" signType="Sign up" signRoute="/signup" />
      <main className="flex-1 flex items-center justify-center py-8">
        <div className="w-full max-w-md p-8 bg-gray-400 rounded-3xl opacity-80">
          <form className="flex flex-col gap-2">
            <Input
              lable="User Name"
              type="text"
              name="userName"
              value={loginData.userName}
              onChange={handleChange}
              error={errors.userName}
            />

            <Input
              lable="Password"
              type="password"
              name="password"
              value={loginData.password}
              onChange={handleChange}
              error={errors.password}
            />
          </form>

          <button
            onClick={handleFormSubmit}
            disabled={!isFormValid(loginData, errors)}
            className="w-full mt-7 bg-blue-700 text-white rounded-3xl h-9 disabled:opacity-50"
          >
            Sign in
          </button>

          <div className="w-fit flex gap-1 p-1 mt-3">
            <span>Don't have an account?</span>
            <Link className="text-blue-900" to={"/signup"}>
              Sign up!
            </Link>
          </div>
        </div>
      </main>
    </div>
  );
}