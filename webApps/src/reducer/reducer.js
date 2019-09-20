
const initialState = {
    color : "primary",
    
}

const reducer = (state = initialState, action) => {
switch(action.type) {
   
    case "success":
        if(state.color !== 'success') {
            state = {color:"success"};
        }           
        break;
       
        case "danger":
        if(state.color !== 'danger') {
            state = { color:"danger"};
        }               
        break;
       
        case "light":
        if(state.color !== 'light') {
            state = { color:"light"};
        }           
        break;
      
        case "dark":
        if(state.color !== 'dark') {
            state = { color:"dark"};
        }           
        break;
        case "warning":
            if(state.color !== 'warning') {
                state = {color:"warning"};
            }           
            break;
    
    case "":
     state={...state, color:"primary"};
    break;
}
console.log("initial state "+JSON.stringify(state));
return state;
};
 export default reducer;