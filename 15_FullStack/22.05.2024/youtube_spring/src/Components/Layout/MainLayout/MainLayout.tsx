import { MainRoute } from "../../Routes/MainRoute/MainRoute";
import { SongList } from "../../pages/SongList/SongList";
import { Header } from "../Header/Header";
import { Main } from "../Main/Main";
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
