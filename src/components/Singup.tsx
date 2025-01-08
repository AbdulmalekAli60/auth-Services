
import { useNavigate } from "react-router-dom";
export default function Singup() {
    const navigator = useNavigate()
  return (
    <div>
      <h1 className="text-yellow-400">Sign up page</h1>
      <button onClick={() => navigator("/")} className="border border-red-700" >Home Pgae</button>

    </div>
  );
}
