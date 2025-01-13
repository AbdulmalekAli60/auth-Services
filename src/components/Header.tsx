import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAlert } from "../contexts/AlertContext";

interface HeaderProps {
  pageTitle: string;
  signType: string;
  signRoute: string;
}

export default function Header({
  pageTitle,
  signType: initialSignType,
  signRoute,
}: HeaderProps) {
  const navigator = useNavigate();
  const { showAlert } = useAlert();
  const [signType, setSignType] = useState(initialSignType);

  useEffect(() => {
    if (sessionStorage.getItem("token") !== null) {
      setSignType("Log Out");
    }
  }, []);

  // Event handlers
  function handleHomePage() {
    if (signType === "Log Out") {
      sessionStorage.removeItem("token");
      showAlert("Logged Out","bg-red-700")
      navigator("/");
      setSignType("Sign up");
    } else {
      navigator(`${signRoute}`);
    }
  }
  // Event handlers

  return (
    <header className=" h-14 sticky bg-slate-950 p-4 flex justify-between items-center   ">
      <h1 className="text-white">Auth and Comments</h1>

      <h1 className="text-center flex-1 text-white text-xl font-extrabold ">
        {pageTitle}
      </h1>

      {/* buttons container */}
      <div className=" text-white">
        <button onClick={() => navigator("/")} className={`mr-5`}>
          Home Page
        </button>

        <button onClick={handleHomePage} className="">
          {signType}
        </button>
      </div>
      {/* buttons container */}
    </header>
  );
}
