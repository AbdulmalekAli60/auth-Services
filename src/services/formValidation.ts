import { FormData } from "../components/Singup";

export interface LogInData {
  userName: string;
  password: string;
}

export interface FormErrors {
  name?: string;
  userName: string;
  email?: string;
  password: string;
}
export const validateField = (name: string, value: string, isLogin: boolean = false): string => {
  let error = "";

  switch (name) {
    case "name":
      if (!isLogin && !value.trim()) {
        error = "Name is required";
      }
      break;

    case "userName":
      if (!value.trim()) {
        error = "Username is required";
      } else if (value.length < 3) {
        error = "Username must be at least 3 characters";
      }
      break;

    case "email": {
      if (!isLogin) {
        const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/;
        if (!value.trim()) {
          error = "Email is required";
        } else if (!emailRegex.test(value)) {
          error = "Invalid email format";
        }
      }
      break;
    }

    case "password": {
      if (!value.trim()) {
        error = "Password is required";
      } else if (!isLogin) {
        // Only apply strict password requirements for signup
        const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
        if (!passwordRegex.test(value)) {
          error = "Password must be at least 8 characters and contain one uppercase letter, one lowercase letter, and one number";
        }
      } else if (value.length < 8) {
        // Simpler validation for login
        error = "Password must be at least 8 characters";
      }
      break;
    }

    default:
      break;
  }

  return error;
};

// Validate entire form for signup
export const validateForm = (formData: FormData): FormErrors => {
  return {
    name: validateField("name", formData.name),
    userName: validateField("userName", formData.userName),
    email: validateField("email", formData.email),
    password: validateField("password", formData.password),
  };
};

// Validate entire form for login
export const validateLoginForm = (loginData: LogInData ): FormErrors => {
  return {
    userName: validateField("userName", loginData.userName, true),
    password: validateField("password", loginData.password, true),
  };
};

// Generic form validation check
export const isFormValid = (data: FormData | LogInData, errors: FormErrors): boolean => {
  return (
    Object.values(data).every((value) => value.trim() !== "") &&
    Object.values(errors).every((error) => error === "")
  );
};
