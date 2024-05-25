import "./register_old.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { UserDetails } from "../../../model/UserDetails";
import { useNavigate } from "react-router-dom";

export function Register_old(): JSX.Element {
      // const [id,setId] = useState(0);
    // const [email,setEmail] = useState("");

    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<UserDetails>();

    const onSubmit: SubmitHandler<UserDetails> = (data) => {
        console.log(data)
        //todo, move to axios :)
    }

    return (
        <div className="Register">
            <div className="Box">
                <h1>Register</h1><hr />
                {/* <input type="text" placeholder="user name..." onChange={(args)=>setEmail(args.target.value)}/><br/><br/> */}
                <form onSubmit={handleSubmit(onSubmit)}>
                    <input type="text" placeholder="user name..." {...register("name",{required:true})}/>
                    <br /><br />
                    <input type="text" placeholder="user email..." {
                        ...register("email", {required:true})}/>
                    <br/>{errors.email && <span style={{color:"red"}}>Email is required</span>}
                    <br /><br />
                    <input type="password" placeholder="user password..." {
                        ...register("password",{required:true, minLength:5, maxLength:10})}/>
                    {errors.password?.type=="required" && <><br/><span style={{color:"red"}}>password is required</span></>}
                    {errors.password?.type=="minLength" && <><br/><span style={{color:"red"}}>password is too short</span></>}
                    {errors.password?.type=="maxLength" && <><br/><span style={{color:"red"}}>password is too long</span></>}
                    <br /><br />
                    <input type="password" placeholder="password check..."/>
                    <br /><br />
                    <input type="text" placeholder="user type..." {...register("userType")}/>
                    <br /><br />
                    <input type="text" placeholder="user tel..." {...register("tel")}/>
                    <br /><br />
                    <input type="text" placeholder="user location..." {...register("location")}/>
                    <br /><br />
                    <input type="text" placeholder="user genre..." {...register("genre")}/>
                    <br />  <br />
                    <hr />
                    <input type="submit" value="register" />
                    <input type="button" value="cancel" onClick={()=>{navigate("/")}}/>
                </form>
            </div>
        </div>
    );
}
