
const initialState = {
    image : require("../image/train1.jpg"),
    
}

const reducer = (state = initialState, action) => {

switch(action.type) {
   
    case "image1":
        if(state.image !== 'image1') {
            state = {image:require("../image/train.jpg")};
        }           
        break;
       
        case "image2":
        if(state.color !== 'image2') {
            state = {image:require("../image/train2.jpg")};
        }           
        break;
       
        case "image3":
        if(state.color !== 'image3') {
            state = {image:require("../image/train3.jpg")};
        }           
        break;
      
        case "image4":
        if(state.color !== 'image4') {
            state = {image:require("../image/train4.jpeg")};
        }           
        break;
       
    
}
console.log("initial state "+JSON.stringify(state));
return state;
};
 export default reducer;