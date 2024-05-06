import "./Header.css";
import uriImage from "../../../Assets/uri.png"
export function Header(): JSX.Element {
    return (
        <div className="Header">
            <div>
            <img src={uriImage}/>
            </div>
            <div>
			<h1>Header - My first website </h1>
            </div>
        </div>
    );
}
