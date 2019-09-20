
import React from 'react';
import Trains from './Trains';
import {Button} from 'react-bootstrap';
import {connect} from "react-redux";

class TrainList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            trains: [],
            isLoaded: false,

        }
        this.fetchTrains = this.fetchTrains.bind(this);
    }

    fetchTrains() {

        fetch("http://localhost:8080/train/all")
            .then(response => response.json())
            .then(result => {
                this.setState({
                    isLoaded: true,
                    trains: result,                   
                })
            });
    }
    render() {
           const {color} =this.props;
        var { trains } = this.state;
        return (
            <div>
                
                <Button onClick={this.fetchTrains} variant={color}>Click to get All trains </Button> <br></br><br></br>
                {
                    trains.map(train =>
                        <div><Trains value={train}></Trains></div>

                    )}
                    
            </div>
        );
    }

}


const mapStateToProps = (state) => {
   
       return {
        color:state.colRd.color
    };
    
 };

export default connect(mapStateToProps)(TrainList);