import React, { Component } from 'react'
import CapacitanceService from '../services/CapacitanceService';

class UpdateCapacitanceComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                multiplier: '',
                unit: ''
        }
        this.updateCapacitance = this.updateCapacitance.bind(this);

        this.changeMultiplierHandler = this.changeMultiplierHandler.bind(this);
        this.changeUnitHandler = this.changeUnitHandler.bind(this);
    }

    componentDidMount(){
        CapacitanceService.getCapacitanceById(this.state.id).then( (res) =>{
            let capacitance = res.data;
            this.setState({
                multiplier: capacitance.multiplier,
                unit: capacitance.unit
            });
        });
    }

    updateCapacitance = (e) => {
        e.preventDefault();
        let capacitance = {
            capacitanceId: this.state.id,
            multiplier: this.state.multiplier,
            unit: this.state.unit
        };
        console.log('capacitance => ' + JSON.stringify(capacitance));
        console.log('id => ' + JSON.stringify(this.state.id));
        CapacitanceService.updateCapacitance(capacitance).then( res => {
            this.props.history.push('/capacitances');
        });
    }

    changeMultiplierHandler= (event) => {
        this.setState({multiplier: event.target.value});
    }
    changeUnitHandler= (event) => {
        this.setState({unit: event.target.value});
    }

    cancel(){
        this.props.history.push('/capacitances');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Capacitance</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Multiplier: </label>
                                            #formFields( $attribute, 'update')
                                            <label> Unit: </label>
                                            #formFields( $attribute, 'update')
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateCapacitance}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateCapacitanceComponent
