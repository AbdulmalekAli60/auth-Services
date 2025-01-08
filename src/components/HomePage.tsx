import { Link, useNavigate } from "react-router-dom";
export default function HomePage() {
  const navigator = useNavigate();
  return (
    <div className=" min-h-screen bg-slate-950 flex flex-col items-center justify-center">
      {/* buttons container */}
      <div className="flex w-full justify-around">
        <Link to={"/signup"}>
          <button className="bg-white rounded-3xl p-7">Singup</button>
        </Link>
        <Link to={"/signin"}>
          <button
            className="bg-white rounded-3xl p-7"
            onClick={() => navigator("/signin")}
          >
            Signin
          </button>
        </Link>
      </div>
      {/*=== buttons container ===*/}
    </div>
  );
}
