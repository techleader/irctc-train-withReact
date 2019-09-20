import React from 'react';
import { Button, Row, Col, Table } from 'react-bootstrap';
import { Link, BrowserRouter as Router, Switch, Route } from "react-router-dom";
import TrainRoute from './TrainRoute';
import TrainsList from './TrainsList';
import '../App.css';
import { connect } from 'react-redux';

class SearchTrain extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            from: "",
            to: "",
            trainBetweenStation: [],
        }
    }

    updateTrain = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })

    }
    fetchTrains = (trainFrom, trainTo) => {

        fetch('http://localhost:8080/train/getTrainBetweenStations?from=' + trainFrom + '&to=' + trainTo)
            .then(response => response.json())
            .then(result => {
                this.setState({
                    trainBetweenStation: result,

                })
            });
    }
    render() {

        return (
            <div class="container">
                <br></br>
                <Row>
                    <Col sm={4}>
                        <div class="border">
                            <br></br>
                            <img src={require('../image/logo1.png')} weight="40px" height="50px" class="inline" />
                            <br></br><br></br>
                            <form>
                                From:<input type="text" name="from" value={this.state.from} onChange={this.updateTrain}></input><br></br><br></br>
                                TO:<input type="text" name="to" value={this.state.to} onChange={this.updateTrain}></input><br></br>
                                <Button onClick={() => this.fetchTrains(this.state.from, this.state.to)} variant="success">Find Train </Button>   <br></br><br></br>
                            </form>
                        </div>
                    </Col>
                    <Col sm={8}>
                        <div class="border">
                            <Router>
                                <TrainsList></TrainsList>
                                {
                                    this.state.trainBetweenStation.map(train =>
                                        <div>
                                            <Table striped bordered hover variant="dark" >
                                                <tbody>
                                                    <tr>
                                                        <td> <Link to="/TrainRoute">{train.trainNumber}</Link></td>
                                                        <td> <Link to="/TrainRoute">{train.trainName}</Link></td>
                                                        <td> {train.startStation}</td>
                                                        <td>{train.endStation}</td>
                                                    </tr>
                                                </tbody>
                                            </Table>
                                            <Switch>
                                                <Route path="/TrainRoute" render={props => <TrainRoute someproper={train.trainNumber}{...props} />}></Route>
                                            </Switch>
                                        </div>
                                    )}
                            </Router>
                        </div>
                    </Col>
                </Row>
            </div>
        );
    }
}

const mapStateToProps = (state) => {

    return {
        color: state.color
    };

};
export default connect(mapStateToProps)(SearchTrain);