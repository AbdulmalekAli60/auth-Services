interface InputProps {
  lable: string;
  name: string;
  type: string;
}
export default function Input({ lable, name, type }: InputProps) {
  return (
    <div className="flex flex-col gap-2">
      <label className="ml-1">{lable}</label>
      <input className="rounded-3xl h-8 p-2" type={type} name={name} />
      <span></span>
    </div>
  );
}
