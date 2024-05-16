import { MainRoute } from "../../Routes/MainRoute/MainRoute";
import { Header } from "../Header/Header";
import { Menu } from "../Menu/Menu";
import { Footer } from "../footer/footer";
import "./MainLayout.css";

export function MainLayout(): JSX.Element {
    return (
        <div className="MainLayout">
			<header>
                <Header/>
            </header>
            <aside>
                <Menu/>
            </aside>
            <main>
                <MainRoute/>
            </main>
            <footer>
                <Footer/>
            </footer>
        </div>
    );
}
