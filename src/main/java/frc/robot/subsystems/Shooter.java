// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final WPI_TalonFX flywheelMotor;
  private final WPI_TalonFX hoodPowerMotor;
  private final CANSparkMax hoodRotatorMotor;
  /** Creates a new Shooter. */
  public Shooter() {
    flywheelMotor = new WPI_TalonFX(SHOOTER_FLYWHEEL_ID);
    hoodPowerMotor = new WPI_TalonFX(SHOOTER_HOOD_POWER_ID);
    hoodRotatorMotor = new CANSparkMax(SHOOTER_HOOD_ROTATOR_ID, MotorType.kBrushless);
    configureAllControllers();
  }

  private void configureAllControllers() {
    configureController(flywheelMotor, Motors.FLYWHEEL);
    configureController(hoodPowerMotor, Motors.HOOD_POWER);
    configureController(hoodRotatorMotor);
  }
  
  public void powerFlywheel() {
    flywheelMotor.set(ControlMode.PercentOutput, 1);
  }

  public void stopFlywheel() {
    flywheelMotor.stopMotor();
  }

  private void configureController(WPI_TalonFX controller, Motors motorType) {
    final double currentLimit = 60;
    final double limitThreshold = 90;
    final double triggerThreshTimeInSec = 1;
    controller.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit, limitThreshold, triggerThreshTimeInSec));
    switch (motorType) {
      case FLYWHEEL: 
        controller.configClosedloopRamp(SHOOTER_RAMP_RATE);
        controller.configOpenloopRamp(SHOOTER_RAMP_RATE);
        break;
      case HOOD_POWER:
      controller.configClosedloopRamp(DRIVE_RAMP_RATE);
      controller.configOpenloopRamp(DRIVE_RAMP_RATE);
    }
    controller.setNeutralMode(NeutralMode.Brake);
  }

  private void configureController(CANSparkMax controller) {
    controller.setClosedLoopRampRate(0.2);
    controller.setOpenLoopRampRate(0.2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

//TODO bad should change
enum Motors {
  FLYWHEEL,
  HOOD_POWER
}
