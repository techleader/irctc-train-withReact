import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';

class GetTrain extends PureComponent {
   constructor(){
       super()
       this.state ={
           train:[]
       }
   }
    
//    FindTrainHandller =() =>{
//         fetch('http://localhost:8080/train/getTrain?trainName='+ trainFrom )
//         .then(response => response.json())
//         .then(result => {
//             this.setState({
//                 train: result,
                
//             })
//         });
//       }
   
    render() {
        return (
            <div>
                {this.state.train.trainName}
            </div>
        );
    }
}



export default GetTrain;