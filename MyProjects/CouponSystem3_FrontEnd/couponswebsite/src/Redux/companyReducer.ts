import { Company } from "../Models/Company";
import { Coupon } from "../Models/Coupon";

//AppState - the data that exists on the application and Redux level
export class CompanyState {
    public companyCoupons: Coupon[] = [];
    public company: Company = {
        id:-1,
        name:"",
        email:"",
        password:"",
    };
}

// ActionType - enum (closed list) of actions that can be performed on the AppState
export enum CompanyActionType {
    clearCompanyState = "clearCompanyState",  
    getAllCompanyCoupons = "getAllCompanyCoupons",
    getAllCompanyCouponsByCategory = "getAllCompanyCouponsByCategory",
    getAllCompanyCouponsByMaxPrice = "getAllCompanyCouponsByMaxPrice",
    getCompanyDetails = "getCompanyDetails",
    addCoupon = "addCoupon",
    deleteCoupon = "deleteCoupon",
    updateCoupon = "updateCoupon",
}

// Action - an object that contains the data required in order to perform the action.  Object is sent to Redux when performing the action.
// Contains: ActionType (action name from the list above) and Payload (data, optional)
export interface CompanyAction {
    type: CompanyActionType,
    payload?: any
}

// Action Creator - Public (external) functions that can be used by the application (work according to the defined rules above)
export function clearCompanyStateAction(): CompanyAction {
    return { type: CompanyActionType.clearCompanyState};
}
export function getAllCompanyCouponsAction(coupons: Coupon[]): CompanyAction {
    return { type: CompanyActionType.getAllCompanyCoupons, payload: coupons };
}
export function getAllCompanyCouponsByCategoryAction(coupons: Coupon[]): CompanyAction {
    return { type: CompanyActionType.getAllCompanyCouponsByCategory, payload: coupons };
}
export function getAllCompanyCouponsByMaxPriceAction(coupons: Coupon[]): CompanyAction {
    return { type: CompanyActionType.getAllCompanyCouponsByMaxPrice, payload: coupons };
}
export function getCompanyDetailsAction(company: Company): CompanyAction {
    return { type: CompanyActionType.getCompanyDetails, payload: company };
}
export function addCouponAction(newCoupon: Coupon): CompanyAction {
    return { type: CompanyActionType.addCoupon, payload: newCoupon };
}
export function deleteCouponAction(id: number): CompanyAction {
    return { type: CompanyActionType.deleteCoupon, payload: id };
}
export function updateCouponAction(coupon: Coupon): CompanyAction {
    return { type: CompanyActionType.updateCoupon, payload: coupon };
}

// Reducer: The function that performs the actions we want to run.
// The function will return the updated AppState
// It is impossible to access the Reducer via the code, only the redux runs the Reducer function
export function CompanyReducer(currentState: CompanyState = new CompanyState(), action: CompanyAction): CompanyState {
    let newState = { ...currentState }; // Creates a copy of the current state
    switch (action.type) {
        case CompanyActionType.clearCompanyState:
            newState = new CompanyState();
            break;
        case CompanyActionType.getAllCompanyCoupons:
            newState.companyCoupons = action.payload;
            break;
        case CompanyActionType.getAllCompanyCouponsByCategory:
            newState.companyCoupons = [...newState.companyCoupons].filter((item) => item.category === action.payload);
            break;
        case CompanyActionType.getAllCompanyCouponsByMaxPrice:
            newState.companyCoupons = [...newState.companyCoupons].filter((item) => item.price <= action.payload);
            break;
        case CompanyActionType.getCompanyDetails:
            newState.company = action.payload;
            break;
        case CompanyActionType.addCoupon:
            newState.companyCoupons = [...newState.companyCoupons, action.payload];
            break;
        case CompanyActionType.deleteCoupon:
            newState.companyCoupons = [...newState.companyCoupons].filter((item) => item.id !== action.payload);
            break;
        case CompanyActionType.updateCoupon:
            // Delete the coupon that needs updating
            newState.companyCoupons = [...newState.companyCoupons].filter((item) => item.id !== action.payload.id);
            // Add the updated coupon
            newState.companyCoupons = [...newState.companyCoupons, action.payload];
            break;
    }
    // Return the new state and update to the current state
    return newState;
}