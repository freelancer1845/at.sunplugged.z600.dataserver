export class Datapoint {

    sessionPK: SessionPK;
    time: Date;

    pressureCryoOne: number;
    pressureCryoTwo: number;
    pressureChamber: number;
    puressureTurboPump: number;
    currentGasFlowSccm: number;
    pinnaclePower: number;
    pinnaclePowerSetpoint: number;
    pinnacleCurrent: number;
    pinnacleVoltage: number;
    ssvOnePower: number;
    ssvOnePowerSetpoint: number;
    ssvOneCurrent: number;
    ssvOneVoltage: number;
    ssvTwoPower: number;
    ssvTwoPowerSetpoint: number;
    ssvTwoCurrent: number;
    ssvTwoVoltage: number;
    conveyorSpeedCombined: number;
    conveyorSpeedLeft: number;
    conveyorSpeedRight: number;
    conveyorEngineLeftMaximum: number;
    conveyorEngineRightMaximum: number;
    conveyorPositionLeft: number;
    conveyorPositionRight: number;
    conveyorPositionCombined: number;
    conveyorMode: string;
    srmChannelTwoLeft: number;
    srmChannelThreeRight: number;



    private propertyNamesOf = <TObj>() => (name: keyof TObj) => name;
    public GetPropertyName = this.propertyNamesOf<this>();

}


export class SessionPK {
    sessionId: number;
    dataPoint: number;
}