import { useNavigate } from "react-router-dom";
import Input from "./Input";
import Header from "./Header";
export default function Singup() {
  const navigator = useNavigate();

  // Event handlers
  function handleFormSubmit(e: React.FormEvent): void {
    e.preventDefault();
    alert("Hi");
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
            <Input lable="Name" type="text" name="name" />
            <Input lable="User Name" type="text" name="username" />
            <Input lable="Email" type="text" name="email" />
            <Input lable="Password" type="text" name="password" />
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
