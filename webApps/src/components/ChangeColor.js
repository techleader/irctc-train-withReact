import React, { Component } from 'react';
import {connect} from "react-redux";
import { Button } from 'react-bootstrap';


class ChangeColor extends Component {
    render() {
        return (
            <div class="container">
                <br></br>                                 
                           <Button type="button" variant="danger" onClick={this.props.danger}>red</Button><div></div>                               
                           <Button type="button" variant="light" onClick={this.props.light}>light</Button><div></div>
                           <Button type="button" variant="black" onClick={this.props.dark}>black</Button><div></div>
                           <Button type="button" variant="success" onClick={this.props.success}>green</Button><div></div>          
                           <Button type="button" variant="warning" onClick={this.props.warning}>yellow</Button><div></div>                                                    
            </div>
        );
    }
}
const mapDispatchToProps = (dispatch) => {
      
    return {
  
       success: () => dispatch({
          type: "success"
         }),
       danger: () => dispatch(
          {
            type: "danger"     
          }
       ),
       light: () => dispatch({
          type: "light"
         
       }),
       dark: () => dispatch({
          type: "dark",
          
       }),
       warning: () => dispatch({
        type: "warning",
       
     })
    };
  };

export default connect(null,mapDispatchToProps)(ChangeColor);