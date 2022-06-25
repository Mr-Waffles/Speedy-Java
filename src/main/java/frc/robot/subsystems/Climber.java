// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;

import static frc.robot.Constants.*;
import frc.robot.utilities.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Climber extends SubsystemBase {
  private final Solenoid climber;
  private final WPI_TalonFX leftMotor;
  private final WPI_TalonFX rightMotor;
  
  private final DigitalInput bottom;
  private final DigitalInput middle;
  private final DigitalInput top;
  public PivotState pivotPosition;

  public Climber() {
    climber = new Solenoid(PCM_CAN_ID, PneumaticsModuleType.CTREPCM, CLIMBER_SOLENOID_ID);
    leftMotor = new WPI_TalonFX(LEFT_CLIMBER_ID);
    rightMotor = new WPI_TalonFX(RIGHT_CLIMBER_ID);

    bottom = new DigitalInput(BOTTOM_OPTICAL_ID);
    middle = new DigitalInput(MIDDLE_OPTICAL_ID);
    top = new DigitalInput(TOP_OPTICAL_ID); 
  }

  public void PivotUp() {
      climber.set(false);
      pivotPosition = PivotState.Up;
  }

  public void PivotDown() {
    climber.set(true);
    pivotPosition = PivotState.Down;
  }

  public PivotState GetPivot() {
    return pivotPosition;
  }

  public void SetPivot(PivotState state) {
    pivotPosition = state;
  }

  public void Raise() {
    leftMotor.set(-1);
    rightMotor.set(1);
  }

  public void Lower() {
    leftMotor.set(1);
    leftMotor.set(-1);
  }

  public void LowerSlow() {
    leftMotor.set(0.1);
    rightMotor.set(-0.1);
  }

  public void Stop() {
    leftMotor.set(0);
    rightMotor.set(0);
  }

  public boolean TooTall() {
    return (
      (GetPivot() == PivotState.Up && GetOpticalSensor(DigitalIDs.middleOptical)) ||
      (GetOpticalSensor(DigitalIDs.middleOptical))
    );
  }

  public void CheckHeight() {
    if (TooTall()) {
        Stop();
    }
  }

  public boolean GetOpticalSensor(DigitalIDs sensor) {
    switch (sensor) {
        case bottomOptical:
            return bottom.get();
        case middleOptical:
            return middle.get();
        case topOptical:
            return top.get();
        default:
            Shuffleboard.getTab("driveData").add("Optical Sensor Get Error", true);
            return false;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
