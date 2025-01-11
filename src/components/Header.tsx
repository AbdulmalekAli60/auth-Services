import { useNavigate } from "react-router-dom";

interface HeaderProps {
    pageTitle:string,
    signType:string
}

export default function Header({pageTitle,signType}:HeaderProps) {
  const navigator = useNavigate();

  if(sessionStorage.getItem("token") === null){
    signType = "Log out"
  }

  return (
    <header className=" h-14 bg-slate-950 p-4 flex justify-between items-center   ">

        <h1 className="text-white">Auth and Comments</h1>
     
        <h1 className="text-center flex-1 text-white text-xl font-extrabold ">
          {pageTitle}
        </h1>

      {/* buttons container */}
      <div className=" text-white">
        <button
          onClick={() => navigator("/")}
          className=" mr-5"
        >
          Home Page
        </button>

        <button
          onClick={() => navigator("/signin")}
          className=""
        >
          {signType}
        </button>
      </div>
      {/* buttons container */}
    </header>
  );
}
