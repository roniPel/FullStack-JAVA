import { Coupon } from "../Models/Coupon";

//AppState - the data that exists on the application and Redux level
export class GuestState {
    public allCoupons: Coupon[] = [];
    public coupon: Coupon = {
        id:-1,
        companyId:-1,
        category:'Food',
        title:"",
        description:"",
        start_date:"",
        end_date:"",
        amount:-1,
        price:-1,
        image:"",
    };
}

// ActionType - enum (closed list) of actions that can be performed on the AppState
export enum GuestActionType {   
    getAllCoupons = "getAllCoupons",
    getOneCoupon = "getOneCoupon",
}

// Action - an object that contains the data required in order to perform the action.  Object is sent to Redux when performing the action.
// Contains: ActionType (action name from the list above) and Payload (data, optional)
export interface GuestAction {
    type: GuestActionType,
    payload?: any
}

// Action Creator - Public (external) functions that can be used by the application (work according to the defined rules above)
export function getAllCouponsAction(coupons: Coupon[]): GuestAction {
    return { type: GuestActionType.getAllCoupons, payload: coupons };
}
export function getOneCouponAction(coupon: Coupon): GuestAction {
    return { type: GuestActionType.getOneCoupon, payload: coupon };
}


// Reducer: The function that performs the actions we want to run.
// The function will return the updated AppState
// It is impossible to access the Reducer via the code, only the redux runs the Reducer function
export function GuestReducer(currentState: GuestState = new GuestState(), action: GuestAction): GuestState {
    const newState = { ...currentState }; // Creates a copy of the current state
    switch (action.type) {
        case GuestActionType.getAllCoupons:
            newState.allCoupons = action.payload;
            break;
        case GuestActionType.getOneCoupon:
            newState.coupon = action.payload;
            break;
    }
    // Return the new state and update to the current state
    return newState;
}