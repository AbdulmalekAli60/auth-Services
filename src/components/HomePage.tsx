import { Link, useNavigate } from "react-router-dom";
import Header from "./Header";
export default function HomePage() {
  const navigator = useNavigate();

  return (
    <div>
      <Header pageTitle="Home Page" signType="Sign up" signRoute="/signup"/>
      <div className=" min-h-screen  flex flex-col items-center justify-center">
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
    </div>
  );
}
