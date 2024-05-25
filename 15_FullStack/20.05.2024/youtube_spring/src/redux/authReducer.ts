export class AuthState {
    email:string = "";
    name:string = "guest";
    token:string = "";
    userType:string = "";
}
 //login, logout, updateToken
 
 //AuthActionType
 export enum AuthActionType{
    login = "login",
    logout = "logout",
    updateToken = "updateToken",
 }

 //AuthAction
 export interface AuthAction{
    type: AuthActionType,
    payload?: any
 }

 //Action Creator
 export function loginAction(user:AuthState): AuthAction {
    return {type: AuthActionType.login, payload:user}
 }

 export function logoutAction(): AuthAction {
    return {type:AuthActionType.logout}
 }

 export function updateTokenAction(token:string): AuthAction {
    return {type:AuthActionType.updateToken, payload:token}
 }

 //Reducer: 
 export function AuthReducer(currentState: AuthState = new AuthState(), action: AuthAction): AuthState {
    let newState = { ...currentState };
    switch(action.type){
        case AuthActionType.login:
            newState = action.payload;
            break;
        case AuthActionType.logout:
            newState = new AuthState();
            break;
        case AuthActionType.updateToken:
            newState.token = action.payload;
            break;
    }
    return newState;
 }