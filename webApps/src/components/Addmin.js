import React, { PureComponent } from 'react';
import { Link, BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Button,Row,Col } from 'react-bootstrap';
import TrainService from './TrainService';
import Routes from './Routes';
import UpdateTrain from './UpdateTrain';
class Addmin extends PureComponent {
    render() {
        return (
            <Router>
                <div>
                    <Row>
                        <Col sm={12}>
                                <Link to="/TrainService"><Button type="button" variant="success">Add New Train</Button></Link>
                               <Link to="/Routes"><Button type="button" variant="success">Add Route</Button></Link>
                               <Link to="/UpdateTrain"><Button type="button" variant="success">Update Train</Button></Link>
                        </Col>
                    </Row>                  
                    <Switch>
                        <Route path="/TrainService" component={TrainService}></Route>
                        <Route path="/Routes" component={Routes}></Route>
                        <Route path="/UpdateTrain" component={UpdateTrain}></Route>
                 </Switch>
                    
                </div>
              
            </Router>

        );
    }
}


export default Addmin;