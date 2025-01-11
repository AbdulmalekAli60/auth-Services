
interface InputProps {
  lable: string;
  name: string;
  type: string;
  value:string,
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void,
  error?:string
}

export default function Input({ lable, name, type,value,onChange, error }: InputProps) {

  return (
    <div className="flex flex-col gap-2">
      <label className="ml-1">{lable}</label>
      <input 
      className="rounded-3xl h-8 p-2" 
      type={type} 
      name={name} 
      value={value}
      onChange={onChange}
      />
      <span>{error}</span>
    </div>
  );
}
