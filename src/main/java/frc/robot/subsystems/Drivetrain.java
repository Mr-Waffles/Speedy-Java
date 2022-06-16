// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Drivetrain extends SubsystemBase {
  private final WPI_TalonFX leftPrimary;
  private final WPI_TalonFX rightPrimary;
  private final WPI_TalonFX leftFollower;
  private final WPI_TalonFX rightFollower;

  private final DifferentialDrive robotDrive;

  private final NetworkTableEntry speedWidget;
  private final NetworkTableEntry rotationWidget;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    leftPrimary = new WPI_TalonFX(LEFT_PRIMARY_ID);
    rightPrimary = new WPI_TalonFX(RIGHT_PRIMARY_ID);
    leftFollower = new WPI_TalonFX(LEFT_FOLLOWER_ID);
    rightFollower = new WPI_TalonFX(RIGHT_FOLLOWER_ID);

    setFollowers();
    setInvert();
    configureAllControllers();

    robotDrive = new DifferentialDrive(leftPrimary, rightPrimary);

    speedWidget = Shuffleboard.getTab("driveData").add("speed", 0).getEntry();
    rotationWidget = Shuffleboard.getTab("driveData").add("rotation", 0).getEntry();
  }

  public void drive(double speed, double rotation ) {
    robotDrive.arcadeDrive(speed * SPEED_PROPORTION, rotation * ROTATION_SPEED_PROPORTION);
    speedWidget.setNumber(speed * SPEED_PROPORTION);
    rotationWidget.setNumber(rotation * ROTATION_SPEED_PROPORTION);
  }

  private void setFollowers() {
    leftFollower.follow(leftPrimary);
    rightFollower.follow(rightPrimary);
  }

  private void setInvert() {
    rightPrimary.setInverted(true);
    rightFollower.setInverted(InvertType.FollowMaster);
    leftPrimary.setInverted(false);
    leftFollower.setInverted(InvertType.FollowMaster);
  }

  private void configureAllControllers() {
    configureController(leftPrimary, false);
    configureController(rightPrimary, false);
    configureController(leftFollower, true);
    configureController(rightFollower, true);
  }

  private void configureController(WPI_TalonFX controller, Boolean isFollower) {
    final double currentLimit = 60;
    final double limitThreshold = 90;
    final double triggerThreshTimeInSec = 1;
    controller.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit, limitThreshold, triggerThreshTimeInSec));
    if (!isFollower) {
        controller.configClosedloopRamp(RAMP_RATE);
        controller.configOpenloopRamp(RAMP_RATE);
    }
    controller.setNeutralMode(NeutralMode.Brake);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}