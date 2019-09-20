import React, { Component } from 'react';
import ChangeColor from './ChangeColor';
import ChangeImage from './ChangeImage';
import { Button, Row, Col, Table } from 'react-bootstrap';
//import '../App.css';


class ChangeTheme extends Component {
    render() {
        return (
            <div class="container">
                <Row>
                    <Col sm={12}>
                        <br></br>
                        <div class="border">
                            <Row>
                                <Col sm={6}>
                                    <br></br>
                                    <h4 class='setColor'>Change Color of Theme</h4>
                                   <ChangeColor></ChangeColor>

                                </Col>
                                <Col sm={6}>
                                    <br></br>
                                    <h4 class='setColor'>Change Images of Theme</h4>
                                    <ChangeImage></ChangeImage>
                                </Col>
                            </Row>
                        </div>
                    </Col>

                </Row>


            </div>
        );
    }
}


export default ChangeTheme;