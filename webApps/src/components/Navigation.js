import React, { Component } from 'react';
import { Nav, Navbar, NavbarBrand, NavItem } from 'react-bootstrap';
import { NavLink } from "react-router-dom";
import { MDBBtn } from "mdbreact";
import { connect } from "react-redux";

class Navigation extends Component {
  render() {
    const { color } = this.props
    return (
      <div>
        <Navbar bg={color}>
          <NavbarBrand >
            <img src={require('../image/logo.png')} weight="40px" height="40px" />
            <h4 className="text-light inline">Welcome to IRCTC, INDIA</h4>
          </NavbarBrand>
          <Nav className="mr-auto">
          </Nav>
          <Nav>
            <NavItem>
              <NavLink to="/Home"><MDBBtn gradient="peach" >Home</MDBBtn></NavLink>
              <NavLink to="/Addmin"><MDBBtn gradient="peach">Admin</MDBBtn></NavLink>
              <NavLink to="/ChangeTheme"><MDBBtn gradient="peach">change Theme</MDBBtn></NavLink>
            </NavItem>
          </Nav>
        </Navbar>
       
      </div>
    );
  }
}

const mapStateToProps = (store) => {
  return {
    color: store.colRd.color,
    image: store.imgRd.image
  };

};
export default connect(mapStateToProps)(Navigation);