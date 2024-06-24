import { Category } from "../Models/Category";
import { Coupon } from "../Models/Coupon"
import { Customer } from "../Models/Customer";

//AppState - the data that exists on the application and Redux level
export class CustomerState {
    public customerCoupons: Coupon[] = [];
    public customerDetails: Customer = {
        id:-1,
        firstName:"",
        lastName:"",
        email:"",
        password:""
    };
    public coupon: Coupon = {
        id:-1,
        companyId:-1,
        category: Category.Automotive,
        title: "",
        description: "",
        start_date: "",
        end_date: "",
        amount: 0,
        price: 0,
        image: "",
    };
}

// ActionType - enum (closed list) of actions that can be performed on the AppState
export enum CustomerActionType {
    clearCustomerState = "clearCustomerState",
    getAllCustomerCoupons = "getAllCustomerCoupons",
    getAllCustomerCouponsByCategory = "getAllCustomerCouponsByCategory",
    getAllCustomerCouponsByMaxPrice = "getAllCustomerCouponsByMaxPrice",
    getOneCouponViaCustomer = "getOneCouponViaCustomer",
    getCustomerDetails = "getCustomerDetails",
    purchaseCoupon = "purchaseCoupon",
}

// Action - an object that contains the data required in order to perform the action.  Object is sent to Redux when performing the action.
// Contains: ActionType (action name from the list above) and Payload (data, optional)
export interface CustomerAction {
    type: CustomerActionType,
    payload?: any
}

// Action Creator - Public (external) functions that can be used by the application (work according to the defined rules above)
export function clearCustomerStateAction(): CustomerAction {
    return { type: CustomerActionType.clearCustomerState};
}
export function getOneCouponViaCustomer(coupon: Coupon): CustomerAction {
    return { type: CustomerActionType.getOneCouponViaCustomer, payload: coupon };
}
export function getAllCustomerCouponsAction(coupons: Coupon[]): CustomerAction {
    return { type: CustomerActionType.getAllCustomerCoupons, payload: coupons };
}
export function getAllCustomerCouponsByCategoryAction(coupons: Coupon[]): CustomerAction {
    return { type: CustomerActionType.getAllCustomerCouponsByCategory, payload: coupons };
}
export function getAllCustomerCouponsByMaxPriceAction(coupons: Coupon[]): CustomerAction {
    return { type: CustomerActionType.getAllCustomerCouponsByMaxPrice, payload: coupons };
}
export function getCustomerDetailsAction(details: Customer): CustomerAction {
    return { type: CustomerActionType.getCustomerDetails, payload: details };
}
export function purchaseCouponAction(coupon: Coupon): CustomerAction {
    return { type: CustomerActionType.purchaseCoupon, payload: coupon };
}


// Reducer: The function that performs the actions we want to run.
// The function will return the updated AppState
// It is impossible to access the Reducer via the code, only the redux runs the Reducer function
export function CustomerReducer(currentState: CustomerState = new CustomerState(), action: CustomerAction): CustomerState {
    let newState = { ...currentState }; // Creates a copy of the current state
    switch (action.type) {
        case CustomerActionType.getOneCouponViaCustomer:
            newState.coupon = action.payload;
            break;
        case CustomerActionType.clearCustomerState:
            newState = new CustomerState();
            break;
        case CustomerActionType.getAllCustomerCoupons:
            newState.customerCoupons = action.payload;
            break;
        case CustomerActionType.getAllCustomerCouponsByCategory:
            newState.customerCoupons = [...newState.customerCoupons].filter((item) => item.category === action.payload);
            break;
        case CustomerActionType.getAllCustomerCouponsByMaxPrice:
            newState.customerCoupons = [...newState.customerCoupons].filter((item) => item.price <= action.payload);
            break;
        case CustomerActionType.getCustomerDetails:
            newState.customerDetails = action.payload;
            break;
        case CustomerActionType.purchaseCoupon:
            // Add the new coupon to the customer coupons list
            newState.customerCoupons = [...newState.customerCoupons, action.payload];
            break;
    }
    // Return the new state and update to the current state
    return newState;
}