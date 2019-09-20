import React, { Component } from 'react';
import { Form,Button } from 'react-bootstrap';

class Login extends Component {
    constructor(props){
        super(props)
        this.state ={
            email: "",
            password:""    
        }
    }

    render() {

        return (
            <div class="container ">
                <br></br>
                <div class="border">
                <Form >
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" />
                        
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" />
                    </Form.Group>
                    <Form.Group controlId="formBasicChecbox">
                        <Form.Check type="checkbox" label="Check me out" />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Submit
        </Button>
                </Form>
                </div>
            </div>
        );
    }
}

export default Login;