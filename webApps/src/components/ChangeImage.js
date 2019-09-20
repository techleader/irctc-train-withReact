import React, { Component } from 'react';
import {connect} from "react-redux";
import { Button } from 'react-bootstrap';
import '../App.css';
class ChangeImage extends Component {
   
    render() {
       const{color} =this.props;
        return (
            <div>
                 
                  <a href="" className="imageHover"> <img src={require('../image/train.jpg')} weight="80px" height="55px"  /> </a>
                  <Button type="button" variant={color} onClick={this.props.image1} size="md">set</Button> <br></br><br></br>
                  <a href="" className="imageHover"> <img src={require('../image/train2.jpg')} weight="80px" height="60px"  /> </a>
                  <Button type="button" variant={color} onClick={this.props.image2} size="md">set</Button>  <br></br><br></br>
                  <a href="" className="imageHover"> <img src={require('../image/train3.jpg')} weight="80px" height="60px"  /> </a>
                  <Button type="button" variant={color} onClick={this.props.image3} size="md">set</Button>  <br></br><br></br>
                  <a href="" className="imageHover"> <img src={require('../image/train4.jpeg')} weight="80px" height="60px"  /> </a>
                  <Button type="button" variant={color} onClick={this.props.image4} size="md">set</Button> <br></br> <br></br>
            </div>
        );
    }
}

const mapDispatchToProps = (dispatch) => {    
    return {  
       image1: () => dispatch({
          type: "image1",
         }),
       image2: () => dispatch({
          type: "image2",         
         
       }),
       image3: () => dispatch({
          type: "image3",
         
       }),
       image4: () => dispatch({
          type: "image4",
          
       })
    };
  };
  const mapStateToProps = (state) => {
   
   return {
   
    color:state.colRd.color
 };
 
 };
export default connect(mapStateToProps,mapDispatchToProps)(ChangeImage);