import React, { PureComponent } from 'react';
import { Button, Row, Col } from 'react-bootstrap';

class AddRoute extends PureComponent {

       constructor(){
            super()
            this.state ={
                trainNumber:"8965",
                route:[],
                routeStatus:""
            }
        }
        setTrainNumberToState =(e) =>{
            this.setState({
                [e.target.name]:e.target.value
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

    addRouteHandller = () => {
        fetch('http://localhost:8080/train/setRoute?route=' + this.state.route + '&trainNum=' + this.state.trainNumber)
          .then(res => res.json)
          .then(result => {
            this.setState({
              route: result,
              routeStatus: "Route for " + this.state.trainNumber + " Train added Succesfully"
            })
          });
        console.log("aa gyo route" + this.state.route)
      }

    render() {
        return (
            <div>
                <Row>
                    <Col sm={12}>
                        <div class="container border">
                            <h1>Add Route </h1>
                            TrainNumber:<input type="text" name="trainNumber" value={this.state.trainNumber} onChange={this.setTrainNumberToState}></input>
                            <div class="inline"><Button type="button" onClick={this.findTrainHandller} variant="danger" >Train</Button></div><br></br><br></br>
                            {/* {showTrain}<br></br> */}
                            Route:<input type="text" name="route" value={this.state.route} onChange={this.addRoute}></input>
                            <div ><Button type="button" onClick={this.addRouteHandller} variant="danger" >Add Route</Button></div><br></br><br></br>
                            <div class="alert alert-primary" role="alert"> <h2> {this.state.routeStatus}</h2></div>
                            <br></br>
                        </div>
                    </Col>
                </Row>
            </div>
        );
    }
}



export default AddRoute;