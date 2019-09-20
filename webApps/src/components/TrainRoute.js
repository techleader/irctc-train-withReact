import React from 'react';

class TrainRoute extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            trainNumber: this.props.someproper,
            route: [],
        }
        this.fetchRoute(this.state.trainNumber);
    }
    fetchRoute(trainNumber) {
        fetch('http://localhost:8080/train/getRouteOfTrain?trainNumber=' + trainNumber)
            .then(response => response.json())
            .then(result => {
                this.setState({ route: result })
            })
    }
    render() {
        return (
            <div>
                <p>
                {
                    this.state.route.map(route => <div>{route}</div>)
                }
                </p>
            </div>
        );
    }
}
export default TrainRoute;