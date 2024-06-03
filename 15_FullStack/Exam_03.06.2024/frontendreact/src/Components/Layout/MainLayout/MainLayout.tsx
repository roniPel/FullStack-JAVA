import { Header } from "../Header/Header";
import "./MainLayout.css";
import { MainRoutes } from "../../Route/MainRoutes/MainRoutes";
import { Footer } from "../Footer/Footer";
import { Menu } from "../Menu/Menu";

export function MainLayout(): JSX.Element {
    return (
        <div className="MainLayout">
			<header>
                <Header />
            </header>
            <div style={{padding:10}}>
                <Menu />
            </div>
            <main>
                <MainRoutes />
            </main>
            <footer>
                <Footer />
            </footer>
        </div>
    );
}
