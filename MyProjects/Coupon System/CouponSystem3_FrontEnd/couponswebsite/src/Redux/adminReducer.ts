import { Company } from "../Models/Company";
import { Customer } from "../Models/Customer";

//AppState - the data that exists on the application and Redux level
export class AdminState {
    public companies: Company[] = [];
    public customers: Customer[] =[];
    public company: Company = {
        id:-1,
        name:"",
        email:"",
        password:"",
    };
    public customer: Customer = {
        id:-1,
        firstName:"",
        lastName:"",
        email:"",
        password:""
    };
}

// ActionType - enum (closed list) of actions that can be performed on the AppState
export enum AdminActionType {
    clearAdminState = "clearAdminState",
    getAllCompanies = "getAllCompanies",
    getAllCustomers = "getAllCustomers",
    getOneCompany = "getOneCompany",
    getOneCustomer = "getOneCustomer",
    addCompany = "addCompany",
    addCustomer = "addCustomer",
    deleteCompany = "deleteCompany",
    deleteCustomer = "deleteCustomer",
    updateCompany = "updateCompany",
    updateCustomer = "updateCustomer",
}

// Action - an object that contains the data required in order to perform the action.  Object is sent to Redux when performing the action.
// Contains: ActionType (action name from the list above) and Payload (data, optional)
export interface AdminAction {
    type: AdminActionType,
    payload?: any
}

// Action Creator - Public (external) functions that can be used by the application (work according to the defined rules above)
export function clearAdminStateAction(): AdminAction {
    return { type: AdminActionType.clearAdminState};
}
export function getAllCompaniesAction(companies: Company[]): AdminAction {
    return { type: AdminActionType.getAllCompanies, payload: companies };
}
export function getAllCustomersAction(customers: Customer[]): AdminAction {
    return { type: AdminActionType.getAllCustomers, payload: customers };
}
export function getOneCompanyAction(company: Company): AdminAction {
    return { type: AdminActionType.getOneCompany, payload: company };
}
export function getOneCustomerAction(customer: Customer): AdminAction {
    return { type: AdminActionType.getOneCustomer, payload: customer };
}
export function addCompanyAction(newCompany: Company): AdminAction {
    return { type: AdminActionType.addCompany, payload: newCompany };
}
export function addCustomerAction(newCustomer: Customer): AdminAction {
    return { type: AdminActionType.addCustomer, payload: newCustomer };
}
export function deleteCompanyAction(id: number): AdminAction {
    return { type: AdminActionType.deleteCompany, payload: id };
}
export function deleteCustomerAction(id: number): AdminAction {
    return { type: AdminActionType.deleteCustomer, payload: id };
}
export function updateCompanyAction(company: Company): AdminAction {
    return { type: AdminActionType.updateCompany, payload: company };
}
export function updateCustomerAction(customer: Customer): AdminAction {
    return { type: AdminActionType.updateCustomer, payload: customer };
}

// Reducer: The function that performs the actions we want to run.
// The function will return the updated AppState
// It is impossible to access the Reducer via the code, only the redux runs the Reducer function
export function AdminReducer(currentState: AdminState = new AdminState(), action: AdminAction): AdminState {
    let newState = { ...currentState }; // Creates a copy of the current state
    switch (action.type) {
        case AdminActionType.clearAdminState:
            newState = new AdminState();
            break;
        case AdminActionType.getAllCompanies:
            newState.companies = action.payload;
            break;
        case AdminActionType.getAllCustomers:
            newState.customers = action.payload;
            break;
        case AdminActionType.getOneCompany:
            newState.company = action.payload;
            break;
        case AdminActionType.getOneCustomer:
                newState.customer = action.payload;
                break;
        case AdminActionType.addCompany:
            newState.companies = [...newState.companies, action.payload];
            break;
        case AdminActionType.addCustomer:
                newState.customers = [...newState.customers, action.payload];
                break;
        case AdminActionType.deleteCompany:
            newState.companies = [...newState.companies].filter((item) => item.id !== action.payload);
            break;
        case AdminActionType.deleteCustomer:
                newState.customers = [...newState.customers].filter((item) => item.id !== action.payload);
                break;
        case AdminActionType.updateCompany:
            // Delete the company that needs updating
            newState.companies = [...newState.companies].filter((item) => item.id !== action.payload.id);
            // Add the updated company
            newState.companies = [...newState.companies, action.payload];
            break;
        case AdminActionType.updateCustomer:
            newState.customers = [...newState.customers].filter((item) => item.id !== action.payload.id);
            newState.customers = [...newState.customers, action.payload];
            break;
    }
    // Return the new state and update to the current state
    return newState;
}