import React from 'react';
import { Table } from 'react-bootstrap';
import { Link, BrowserRouter as Router, Switch, Route } from "react-router-dom";
import TrainRoute from './TrainRoute';
import '../App.css';
import {connect} from "react-redux";

class Trains extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      trains: props.value,
    }
  }
  render() {

    const{trainName,trainNumber,startStation,endStation} = this.state.trains
    return (
      <div >
        <Router>
          <Table striped bordered hover variant={this.props.color}>
            <tbody>
              <tr >
               <td ><Link to="/TrainRoute" class="changeColor">{trainNumber}</Link> </td>
                <td ><Link to="/TrainRoute" class="changeColor">{trainName}</Link> </td>
                <td> {startStation}</td>
                <td>{endStation}</td>
              
            </tr>
            </tbody>
          </Table>

          <Switch>
              <Route path="/TrainRoute" render={props => <TrainRoute someproper={trainNumber}{...props} />}></Route>
          </Switch>
        </Router>
      </div>

    );
  }
}
const mapStateToProps = (state) => {
   
  return {
   color:state.colRd.color
};

};
export default connect(mapStateToProps)(Trains);