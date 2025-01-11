import { useNavigate } from "react-router-dom";
import Header from "./Header";
import Input from "./Input";
import { useState } from "react";

interface LogInData {
  userName: string;
  password: string;
}
export default function Singin() {
  const navigator = useNavigate();

  const [LogInData, setLogInData] = useState<LogInData>({
    userName: "",
    password: "",
  });

  // Event handlers
  function handleChaneg(e:React.ChangeEvent<HTMLInputElement>){
    const {name,value} = e.target
    setLogInData((prev) => ({
      ...prev,
      [name]:value
    }))
  }

  function handleFormSubmit(e: React.FormEvent): void {
    e.preventDefault();
    // api request
  }
  // Evenet Handlers
  return (
    <div>
      <Header pageTitle="Sign in Page" signType="Sign up" />
      <main className="flex-1  flex items-center justify-center py-8">
        {/* <h1 className="text-center">Sign up</h1> */}

        {/* form container */}
        <div className="w-full max-w-md p-8  bg-gray-400 rounded-3xl opacity-80">
          {/* form */}
          <form className="flex flex-col gap-2">
            <Input
              lable="User Name"
              type="text"
              name="userName"
              value={LogInData.userName}
              onChange={handleChaneg}
            />

            <Input
              lable="Password"
              type="text"
              name="password"
              value={LogInData.password}
              onChange={handleChaneg}

            />
          </form>

          {/* form */}
          <button
            onClick={(e) => handleFormSubmit(e)}
            className="w-full mt-7  bg-blue-700 text-white rounded-3xl h-9"
          >
            Sign in
          </button>
        </div>

        {/* form container */}
      </main>
    </div>
  );
}
