import axios from 'axios';
import React from 'react';
import { Button, Row, Col } from 'react-bootstrap';
import { } from 'mdbreact'; 

class AddNewTrain extends React.Component {
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

  //{.............add new train handler...........}  

  addNewTrainHandller = (e) => {
    e.preventDefault()
    var fd= new FormData();
    fd.append("trainNum",this.state.trainNumber);
    fd.append("trainName",this.state.trainName);
    fd.append("start",this.state.startStation);
    fd.append("end",this.state.endStation);
    fd.append("route",this.state.endStation);
    axios.post('http://localhost:8080/train/saveNewTrain',fd)
      .then(respose => {
        this.setState({
          status: "New Train " + this.state.trainName + " Succesfully Added",
        })
      })
      .catch(error => {
        console.log("error aa gi " + error)
      })
  }

  render() {
    
    return (

      <div className="container">
        <Row>
          <Col sm={12}>
            <div class="border">
              <form >
                <h1>Add New Train</h1>
                <br></br>

                Train Number:<input type="text" name="trainNumber" value={this.state.trainNumber} onChange={this.setTrainToState}></input><br></br><br></br>
                Train Name:<input type="text" name="trainName" value={this.state.trainName} onChange={this.setTrainToState}></input><br></br><br></br>
                Start Station:<input type="text" name="startStation" value={this.state.startStation} onChange={this.setTrainToState}></input><br></br><br></br>
                End Station:<input type="text" name="endStation" value={this.state.endStation} onChange={this.setTrainToState}></input><br></br><br></br>
                <Button type="submit" onClick={this.addNewTrainHandller} variant="success">ADD Train </Button>
                <div class="alert alert-primary" role="alert"> <h2> {this.state.status}</h2></div>

              </form>
            </div>
          </Col>
        </Row>
      </div>
    );
  }

}
export default AddNewTrain;