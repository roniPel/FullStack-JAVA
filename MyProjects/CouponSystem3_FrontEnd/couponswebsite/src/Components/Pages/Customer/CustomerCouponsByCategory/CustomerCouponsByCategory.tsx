import { useNavigate } from "react-router-dom";
import "./CustomerCouponsByCategory.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { checkData } from "../../../../Utilities/checkData";
import axios from "axios";
import { getAllCustomerCouponsAction } from "../../../../Redux/customerReducer";
import { Typography } from "@mui/material";
import { Category } from "../../../../Models/Category";
import { getValue } from "@testing-library/user-event/dist/utils";
import { SingleCoupon } from "../../General/SingleCoupon/SingleCoupon";

export function CustomerCouponsByCategory(): JSX.Element {
    const navigate = useNavigate();
    const [couponList, setList] = useState<Coupon[]>([]);
    const [selectedCategory, setCategory] = useState<Category>();
    const [filteredCouponList, setFilteredList] = useState<Coupon[]>([]);

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Customer){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        checkData();
        getCustCoupons();
    },[]);

    function getCustCoupons(){
        // check if requested customer coupons exist in redux
        if(couponStore.getState().customer.customerCoupons !== null){
            setList(couponStore.getState().customer.customerCoupons);
        } else {    // If redux is empty
            let recivedList:Coupon[] = [];
            // Get coupons from DB
            axios.get("http://localhost:8080/Customer/GetCustomerCoupons")
            .then(result=>{
                //console.log("Axios result: "+result)
            for (let index=0;index<result.data.length;index++){
                recivedList.push(new Coupon(
                    result.data[index].id,
                    result.data[index].companyId,
                    result.data[index].category,
                    result.data[index].title,
                    result.data[index].description,
                    result.data[index].start_date,
                    result.data[index].end_date,
                    result.data[index].amount,
                    result.data[index].price,
                    result.data[index].image
                ));
            };
            setList(recivedList);                   
            couponStore.dispatch(getAllCustomerCouponsAction(recivedList));
            setList(couponStore.getState().customer.customerCoupons);
            })
            .catch(err=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");              
            });
        }
    }

    function filterCategory(){
        let myList = couponList.filter( (coup)=>{
            coup.category === selectedCategory;
        });
        setFilteredList(myList);
    }

    return (
        <div>
            <Typography variant="h4" className="HeadLine">My Coupons by Category</Typography>
            <hr />
            <div className="CustomerCouponsByCategory">
                <div className="Select Category Box">
                    <label>Select Category:</label><br />
                    <select onChange={(args)=> {
                        setCategory(args.target.value as Category); 
                        console.log(selectedCategory);
                        filterCategory();
                        }} >
                        {couponList.map((item)=><option key={item.id} value={item.category}>{item.category as string}</option>)}
                    </select><br />
                </div>
                <div className="Coupon List Result">
                    {filteredCouponList.map((item)=><SingleCoupon key={item.id} coupon={item}/>)}
                </div>
            </div>
        </div>
    );
}
