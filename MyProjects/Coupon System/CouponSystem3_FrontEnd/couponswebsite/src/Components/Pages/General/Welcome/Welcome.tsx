import { Button, Typography } from "@mui/material";
import "./Welcome.css";
import { useNavigate } from "react-router-dom";

export function Welcome(): JSX.Element {
    
    //react hooks => useState , useEffect
    const navigate = useNavigate();

    return (
        <div className="Welcome">
            <br/><Typography variant="h4" className="HeadLine">Welcome</Typography><hr/>
            <div className="Box" style={{width:"60%"}}>
                <Typography variant="h6" className="HeadLine">The coupon system manages coupons for companies and customers.</Typography>
                <Typography variant="h6" className="HeadLine">You are welcome to browse through our website, buy coupons you like, 
                or join us as a company and enjoy sales, deals, and great customer service.</Typography>
            </div>
            <br/><br/>
            <Button variant="contained" color="primary" onClick={() => { navigate("/allCoupons") }}>Show me!</Button>
        </div>
    );

}
