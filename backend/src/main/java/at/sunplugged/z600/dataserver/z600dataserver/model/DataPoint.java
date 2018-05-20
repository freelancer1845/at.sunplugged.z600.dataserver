package at.sunplugged.z600.dataserver.z600dataserver.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class DataPoint {

	@EmbeddedId
	private SessionPK sessionPK;

	@NotNull
	@Column(name = DATE)
	private LocalDateTime time;

	@Column(name = PRESSURE_CRYO_ONE)
	private double pressureCryoOne;

	@Column(name = PRESSURE_CRYO_TWO)
	private double pressureCryoTwo;

	@Column(name = PRESSURE_CHAMBER)
	private double pressureChamber;

	@Column(name = PERSSURE_TMP)
	private double pressureTurboPump;

	@Column(name = CURRENT_GAS_FLOW_SCCM)
	private double currentGasFlowSccm;

	@Column(name = PINNACLE_POWER)
	private double pinnaclePower;

	@Column(name = PINNACLE_POWER_SETPOINT)
	private double pinnaclePowerSetpoint;

	@Column(name = PINNACLE_CURRENT)
	private double pinnacleCurrent;

	@Column(name = PINNACLE_VOLTAGE)
	private double pinnacleVoltage;

	@Column(name = SSV_ONE_POWER)
	private double ssvOnePower;

	@Column(name = SSV_ONE_POWER_SETPOINT)
	private double ssvOnePowerSetpoint;

	@Column(name = SSV_ONE_CURRENT)
	private double ssvOneCurrent;

	@Column(name = SSV_ONE_VOLTAGE)
	private double ssvOneVoltage;

	@Column(name = SSV_TWO_POWER)
	private double ssvTwoPower;

	@Column(name = SSV_TWO_POWER_SETPOINT)
	private double ssvTwoPowerSetpoint;

	@Column(name = SSV_TWO_CURRENT)
	private double ssvTwoCurrent;

	@Column(name = SSV_TWO_VOLTAGE)
	private double ssvTwoVoltage;

	@Column(name = CONVEYOR_SPEED_COMBINED)
	private double conveyorSpeedCombined;

	@Column(name = CONVEYOR_SPEED_LEFT)
	private double conveyorSpeedLeft;

	@Column(name = CONVEYOR_SPEED_RIGHT)
	private double conveyorSpeedRight;

	@Column(name = CONVEYOR_SPEED_SETPOINT)
	private double conveyorSpeedSetpoint;

	@Column(name = CONVEYOR_ENGINE_LEFT_MAXIMUM)
	private double conveyorEngineLeftMaximum;

	@Column(name = CONVEYOR_ENGINE_RIGHT_MAXIMUM)
	private double conveyorEngineRightMaximum;

	@Column(name = CONVEYOR_POSITION_LEFT)
	private double conveyorPositionLeft;

	@Column(name = CONVEYOR_POSITION_RIGHT)
	private double conveyorPositionRight;

	@Column(name = CONVEYOR_POSITION_COMBINED)
	private double conveyorPositionCombined;

	@Column(name = CONVEYOR_MODE)
	private String conveyorMode;

	@Column(name = SRM_CHANNEL_2_LEFT)
	private double srmChannelTwoLeft;

	@Column(name = SRM_CHANNEL_3_RIGHT)
	private double srmChannelThreeRight;

	@Data
	@Embeddable
	public static class SessionPK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8491720672704257250L;

		@NotNull
		@Column(name = SESSION)
		protected Long sessionId;

		@NotNull
		@Column(name = DATA_POINT)
		protected Long dataPoint;
	}

	public interface DatapointSessionData {
		SessionPK getSessionPK();

		LocalDateTime getTime();
	}

	/** ID. */
	public static final String SESSION = "Session";

	/** ID. */
	public static final String DATA_POINT = "DataPoint";

	/** ID. */
	public static final String DATE = "`Date`";

	/** ID. */
	public static final String PRESSURE_CRYO_ONE = "PressureCryoOne";

	/** ID. */
	public static final String PRESSURE_CRYO_TWO = "PressureCryoTwo";

	/** ID. */
	public static final String PRESSURE_CHAMBER = "PressureChamber";

	/** ID. */
	public static final String PERSSURE_TMP = "PressureTurbopump";

	/** ID. */
	public static final String CURRENT_GAS_FLOW_SCCM = "GasFlowSccm";

	/** ID. */
	public static final String PINNACLE_POWER = "PinnaclePower";

	/** ID. */
	public static final String PINNACLE_POWER_SETPOINT = "PinnaclePowerSetpoint";

	/** ID. */
	public static final String PINNACLE_CURRENT = "PinnacleCurrent";

	/** ID. */
	public static final String PINNACLE_VOLTAGE = "PinnacleVoltage";

	/** ID. */
	public static final String SSV_ONE_POWER = "SSVOnePower";

	/** ID. */
	public static final String SSV_ONE_POWER_SETPOINT = "SSVOnePowerSetpoint";

	/** ID. */
	public static final String SSV_ONE_CURRENT = "SSVOneCurrent";

	/** ID. */
	public static final String SSV_ONE_VOLTAGE = "SSVOneVoltage";

	/** ID. */
	public static final String SSV_TWO_POWER = "SSVTwoPower";

	/** ID. */
	public static final String SSV_TWO_POWER_SETPOINT = "SSVTwoPowerSetpoint";

	/** ID. */
	public static final String SSV_TWO_CURRENT = "SSVTwoCurrent";

	/** ID. */
	public static final String SSV_TWO_VOLTAGE = "SSVTwoVoltage";

	/** ID. */
	public static final String CONVEYOR_SPEED_COMBINED = "SpeedCombined";

	/** ID. */
	public static final String CONVEYOR_SPEED_LEFT = "SpeedLeft";

	/** ID. */
	public static final String CONVEYOR_SPEED_RIGHT = "SpeedRight";

	/** ID. */
	public static final String CONVEYOR_SPEED_SETPOINT = "SpeedSetpoint";

	/** ID. */
	public static final String CONVEYOR_ENGINE_LEFT_MAXIMUM = "EngineLeftMaximum";

	/** ID. */
	public static final String CONVEYOR_ENGINE_RIGHT_MAXIMUM = "EngineRightMaximum";

	/** ID. */
	public static final String CONVEYOR_POSITION_LEFT = "ConveyorPositionFromLeft";

	/** ID. */
	public static final String CONVEYOR_POSITION_RIGHT = "ConveyorPositionFromRight";

	/** ID. */
	public static final String CONVEYOR_POSITION_COMBINED = "ConveyorPositionCombined";

	/** ID. */
	public static final String CONVEYOR_MODE = "ConveyorMode";

	/** ID. */
	public static final String TARGET_WORK_DONE = "kWh";

	/** ID. */
	public static final String SRM_CHANNEL_2_LEFT = "SrmLeftChannel2";

	/** ID. */
	public static final String SRM_CHANNEL_3_RIGHT = "SrmRightChannel3";

}
