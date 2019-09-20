import React, { Component } from 'react';
import { connect } from 'react-redux';
import AddNewTrain from './AddNewTrain';



class TrainService extends Component {
    render() {
        return (
            <div>
                <AddNewTrain></AddNewTrain>
            </div>
        );
    }
}

export default (TrainService);