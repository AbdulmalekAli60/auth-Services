import { X } from "lucide-react";
import { useEffect, useState } from "react";

interface AlertProps {
  alertColor: string;
  alertMessage: string;
  show: boolean;
}
export default function Alert({ alertColor, alertMessage, show }: AlertProps) {
  const [alertState, setAlertState] = useState<boolean>(show);

  useEffect(() => {
    const myTimeOut = setTimeout(() => {
      setAlertState(false);
    }, 2000);

    return () => {
      clearTimeout(myTimeOut);
    };
  }, [alertState]);

  return (
    <div className="">
      <div
        className={`fixed bottom-4 left-4 z-50 w-8/12  ${
          !alertState && "invisible"
        }`}
      >
        <div
          role="alert"
          className={`mb-4 relative flex w-4/12 p-3 text-white ${alertColor} rounded-md`}
        >
          {alertMessage}
          <button
            className="flex items-center justify-center  w-8 h-8 rounded-md text-white absolute top-1.5 right-1.5"
            type="button"
            onClick={() => setAlertState(false)}
          >
            <X className="h-5 w-5" />
          </button>
        </div>
      </div>
    </div>
  );
}
