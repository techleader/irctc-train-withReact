import React, { Fragment } from 'react';
import { Component } from 'react';
import { BrowserRouter as Router, NavLink, Route, Switch } from "react-router-dom";
import Addmin from './components/Addmin';
import ChangeTheme from './components/ChangeTheme';
import { connect } from 'react-redux';
import Home from './components/Home';
import Navigation from './components/Navigation';
import login from './components/Login';
import Login from './components/Login';

class App extends Component {

  render() {
    const{image} =this.props;
  
       const changeimage ={
          backgroundImage: `url(${image})`,
     
    };
    return (
      <div className="App App-header "style={changeimage}>        
       
        <Router> 
          <Navigation></Navigation>           
          <Switch>      
            <Route path="/Home" component={Home}></Route>
            <Route path="/Addmin" component={Addmin}></Route>
            <Route path="/ChangeTheme" component={ChangeTheme}></Route>
          </Switch>
        </Router> 
        <Login></Login>
        </div>                  
     
    );
  }
}

const mapStateToProps = (state) => {
   
  return {
  
   image:state.imgRd.image
};

};
export default connect(mapStateToProps)(App);
