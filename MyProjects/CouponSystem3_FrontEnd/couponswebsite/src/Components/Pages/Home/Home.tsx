import { Typography } from "@mui/material";
import "./Home.css";

export function Home(): JSX.Element {
    return (
        <div className="Home">
			<br/><Typography variant="h4" className="HeadLine">Home</Typography><br/>
            <div className="Box" style={{ width: "80%" }}>
                <br/><Typography variant="subtitle1" className="Title1">A bit about us</Typography><br/>
            </div>
        </div>
    );
}
