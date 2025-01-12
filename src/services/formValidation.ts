import { FormData } from "../components/Singup";

// export interface FormData {
//     name: string;
//     userName: string;
//     email: string;
//     password: string;
//   }

export interface FormErrors {
  name: string;
  userName: string;
  email: string;
  password: string;
}

export const validateField = (name: string, value: string): string => {
  let error = "";

  switch (name) {
    case "name":
      if (!value.trim()) {
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
      const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/;
      if (!value.trim()) {
        error = "Email is required";
      } else if (!emailRegex.test(value)) {
        error = "Invalid email format";
      }
      break;
    }

    case "password": {
      const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
      if (!value.trim()) {
        error = "Password is required";
      } else if (!passwordRegex.test(value)) {
        error =
          "Password must be at least 8 characters and one uppercase letter, one lowercase letter, and one number";
      }
      break;
    }

    default:
      break;
  }

  return error;
};

export const validateForm = (formData: FormData): FormErrors => {
  return {
    name: validateField("name", formData.name),
    userName: validateField("userName", formData.userName),
    email: validateField("email", formData.email),
    password: validateField("password", formData.password),
  };
};

export const isFormValid = (
  formData: FormData,
  errors: FormErrors
): boolean => {
  return (
    Object.values(formData).every((value) => value.trim() !== "") &&
    Object.values(errors).every((error) => error === "")
  );
};
