import { useNavigate } from "react-router-dom";

export default function Singin() {
    const navigator = useNavigate()
  return (
    <div>
      <h1>Sign in page</h1>
      <button onClick={() => navigator("/")} className="border border-red-700" >Home Pgae</button>
    </div>
  );
}
