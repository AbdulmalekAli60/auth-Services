import { useNavigate } from "react-router-dom";
import Input from "./Input";
import Header from "./Header";
import { useState } from "react";

interface formData {
  name: string;
  userName: string;
  email: string;
  password: string;
}
export default function Singup() {
  const navigator = useNavigate();

  const [formData, setFormData] = useState<formData>({
    name: "",
    userName: "",
    email: "",
    password: "",
  });

  // Event handlers

  function handleChaneg(e: React.ChangeEvent<HTMLInputElement>) {
    // 1- take the name of the input and the value, 2- set each name to its corresponding value
    const {name, value} = e.target // give us the input that has chnaged
    setFormData((prev) => ({
      ...prev,
      [name]:value
    }));
  }
  
  function handleFormSubmit(e: React.FormEvent): void {
    e.preventDefault();
    // api request is here
    console.log("form data: ", formData)
  }
  // Evenet Handlers

  return (
    <div>
      <Header pageTitle="Sign up Page" signType="Sign in" />;
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
            />
            <Input
              lable="User Name"
              type="text"
              name="userName"
              value={formData.userName}
              onChange={handleChaneg}
            />
            <Input
              lable="Email"
              type="text"
              name="email"
              value={formData.email}
              onChange={handleChaneg}
            />
            <Input
              lable="Password"
              type="text"
              name="password"
              value={formData.password}
              onChange={handleChaneg}
            />
          </form>
          {/* form */}

          <button
            onClick={(e) => handleFormSubmit(e)}
            className="w-full mt-7  bg-blue-700 text-white rounded-3xl h-9"
          >
            Join
          </button>
        </div>

        {/* form container */}
      </main>
    </div>
  );
}
