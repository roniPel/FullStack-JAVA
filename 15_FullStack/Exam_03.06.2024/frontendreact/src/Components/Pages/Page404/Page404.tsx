import { Typography } from "@mui/material";
import "./Page404.css";

export function Page404(): JSX.Element {
    return (
        <div className="Page404">
			<br/><Typography variant="h4" className="Title">Oh No... Page 404</Typography><br/>
			<iframe width="560" height="315" 
            src="https://www.youtube.com/embed/t3otBjVZzT0?autoplay=1" 
            title="YouTube video player" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
            referrerPolicy="strict-origin-when-cross-origin" allowFullScreen ></iframe>
        </div>
    );
}
