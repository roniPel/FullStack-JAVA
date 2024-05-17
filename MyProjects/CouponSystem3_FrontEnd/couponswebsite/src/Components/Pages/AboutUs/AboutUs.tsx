import { Button, Typography } from "@mui/material";
import "./AboutUs.css";
import { useNavigate } from "react-router-dom";

export function AboutUs(): JSX.Element {
    const navigate = useNavigate();
    return (
        <div className="AboutUs">
			<br/><Typography variant="h4" className="HeadLine">We are an Open Book</Typography><br/>
            <div className="Box" style={{ width: "60%" }} >
                <Typography variant="h5" className="Title1" align="left">A bit about us...</Typography><br/>
                <div>
                    <Typography variant="body1" className="Body1" align="left">
                        On our coupons site you can find almost any type of coupon you desire!<br/>
                        Our website is very friendly and easy-to-use.<br/>
                    </Typography><br/><br/>
                    <Button color="primary" variant="contained"  onClick={() => { navigate("/") }}>Let the shopping begin!</Button>
                </div>
            </div>
            <div className="Box" style={{ width: "60%" }}>
                <Typography variant="h5" className="Title1" align="left">How it all began...</Typography><br/>
                <div>
                    <Typography variant="body1" className="Body2" align="left">
                        My name is Roni Peled and I was a student at JohnBryce College.<br/>
                        I took the FullStack JAVA Course, class of Nov. 2023.<br/>
                        As part of our final project in the course, we needed to build a coupon system for different users.<br/>
                        That was the start of this website. <br/>
                        It would take a few months longer to learn how to (and eventually) create the rest of the project. <br/>
                        Through hard work, determination, and a 'bit' of help from our awesome lecturer, Zeev Mindali, you can now enjoy the final result...
                    </Typography><br/><br/>
                    <Button color="primary" variant="contained"  onClick={() => { navigate("/") }}>I want to see everything!</Button>
                </div>
            </div>
        </div>
    );
}
