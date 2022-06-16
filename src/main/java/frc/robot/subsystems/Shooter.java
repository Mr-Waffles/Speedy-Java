// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final WPI_TalonFX shootMotor;
  /** Creates a new Shooter. */
  public Shooter() {
    shootMotor = new WPI_TalonFX(5);
  }

  public void configure() {
    configureController(shootMotor);
  }
  
  public void go() {
    shootMotor.set(ControlMode.PercentOutput, 1);
  }

  public void stop() {
    shootMotor.stopMotor();
  }

  private void configureController(WPI_TalonFX controller) {
    final double currentLimit = 60;
    final double limitThreshold = 90;
    final double triggerThreshTimeInSec = 0.75;
    controller.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit, limitThreshold, triggerThreshTimeInSec));
    controller.configClosedloopRamp(1);
    controller.configOpenloopRamp(1);
    controller.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
