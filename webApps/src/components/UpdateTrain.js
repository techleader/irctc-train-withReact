import React, { Component } from 'react';
import { Button,Row,Col } from 'react-bootstrap';
import axios from 'axios';


class UpdateTrain extends Component {
  constructor(props) {
    super(props)
    this.state = {
      trainName: "",
      trainNumber: "",
      startStation: "",
      endStation: "",
      route: [],
      status: "",
      train: {},
      flag: "false"
    }
  }

  setTrainToState = (e) => {
    this.setState({
      [e.target.name]: e.target.value,
    })
  }

  //{.......find train handler...............}

  findTrainHandller = (trainNum) => {
    fetch('http://localhost:8080/train/getTrain?trainNum=' + trainNum)
      .then(response => response.json())
      .then(result => {
        this.setState({
          train: result,
          flag: "true"
        })
      });
    return (this.state.train);
  }
  // {.............update train handler.......................}

  updateHandller = (e) => {
    e.preventDefault()
    axios.post('http://localhost:8080/train/updateTrain?trainNum=' + this.state.trainNumber + '&trainName=' + this.state.trainName +
      '&start=' + this.state.startStation + '&end=' + this.state.endStation + '&route=' + this.state.startStation + "," + this.state.endStation)
      .then(respose => {
        this.setState({
          status: "Train " + this.state.trainName + " updated Succesfully",
        })
      })
      .catch(error => {
        console.log("error aa gi " + error)
      })
  }
    render() {
      var show = this.state.flag;
    var showTrain = "";
    if (show === "true") {
      showTrain = <div class="alert alert-dark" role="alert">
        {this.state.train.trainNumber},{this.state.train.trainName},{this.state.train.startStation},{this.state.train.endStation}
      </div>
    }

        return (
            <div>
                <div className="container">
        <Row>
          <Col sm={12}>
            <div class="border">
              <form >
                <h1>Update Train</h1>
                <br></br>
                Train Number:<input type="text" name="trainNumber" value={this.state.trainNumber} onChange={this.setTrainToState}></input>
                <div class="inline">
                  <Button type="button" onClick = {() => this.findTrainHandller(this.state.trainNumber)} variant="danger" >Find Train</Button>
                </div><br></br><br></br>
                {showTrain}
                Train Name:<input type="text" name="trainName" value={this.state.trainName} onChange={this.setTrainToState}></input><br></br><br></br>
                Start Station:<input type="text" name="startStation" value={this.state.startStation} onChange={this.setTrainToState}></input><br></br><br></br>
                End Station:<input type="text" name="endStation" value={this.state.endStation} onChange={this.setTrainToState}></input><br></br><br></br>               
                <div class="inline"><Button type="submit" onClick={this.updateHandller} variant="success">Update Train </Button></div><br></br>
                <div class="alert alert-primary" role="alert"> <h2> {this.state.status}</h2></div>

              </form>
            </div>
          </Col>
        </Row>
      </div>
            </div>
        );
    }  
}

export default UpdateTrain;