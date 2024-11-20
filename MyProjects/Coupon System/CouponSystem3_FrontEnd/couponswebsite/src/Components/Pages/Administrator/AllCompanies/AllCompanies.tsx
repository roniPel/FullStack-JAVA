import { useNavigate } from "react-router-dom";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import "./AllCompanies.css";
import { useEffect, useState } from "react";
import notify from "../../../../Utilities/notify";
import { Typography } from "@mui/material";
import { Company } from "../../../../Models/Company";
import { checkData } from "../../../../Utilities/checkData";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { getAllCompaniesAction } from "../../../../Redux/adminReducer";
import { SingleCompany } from "../SingleCompany/SingleCompany";

export function AllCompanies(): JSX.Element {
    const navigate = useNavigate();
    const [companyList, setList] = useState<Company[]>([]);

    couponStore.subscribe(()=>{
        setList(couponStore.getState().admin.companies);
    });

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }

        //get company list from backend
        let recivedList:Company[] = [];

        // Check that our redux data is updated
        checkData();
        //check if we have any data in redux
        if (couponStore.getState().admin.companies.length <= 1) {
            axiosJWT.get("http://localhost:8080/Admin/GetAllCompanies")
            .then(result=>{
                for (let index=0;index<result.data.length;index++){
                    recivedList.push(new Company(
                        result.data[index].id,
                        result.data[index].name,
                        result.data[index].email,
                        result.data[index].password
                    ));
                };
                setList(recivedList);                   
                couponStore.dispatch(getAllCompaniesAction(recivedList));
                setList(couponStore.getState().admin.companies);
            })
            .catch((err)=>{
                navigate("/login")
            });
        } else {
            setList(couponStore.getState().admin.companies);
        }
    },[]);
    
    return (
        <div className="AllCompanies">
			<br/><Typography variant="h4" className="HeadLine">All Companies</Typography><hr/><br/>
            {companyList.map(item=><SingleCompany key={item.id} company={item}/>)}
        </div>
    );
}
