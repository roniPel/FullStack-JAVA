import { Header } from "../Header/Header";
import { Menu } from "../Menu/Menu";
import { Main } from "../Main/Main";
import "./MainLayout.css";
import { Footer } from "../Footer/Footer";

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
                <Main/>
            </main>
            <footer>
                <Footer/>
            </footer>
        </div>
    );
}
