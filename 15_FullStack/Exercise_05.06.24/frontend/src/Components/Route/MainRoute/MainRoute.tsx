import { Main } from "../../Layout/Main/Main";
import { AddGroup } from "../../Pages/AddGroup/AddGroup";
import { AddMetting } from "../../Pages/AddMetting/AddMetting";
import { GroupList } from "../../Pages/GroupList/GroupList";
import { Page404 } from "../../Pages/Page404/Page404";
import "./MainRoute.css";
import {Routes,Route} from "react-router-dom";
export function MainRoute(): JSX.Element {
    return (
        <div className="MainRoute">
			<Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/meeting/add" element={<AddMetting/>}/>
                <Route path="/groups" element={<GroupList/>}/>
                <Route path="/groups/add" element={<AddGroup/>}/>
                <Route path="*" element={<Page404/>}/>
            </Routes>
        </div>
    );
}
