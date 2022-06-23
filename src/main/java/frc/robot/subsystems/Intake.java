// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.HSendableBuilder;

import static frc.robot.Constants.*;

public class Intake extends SubsystemBase {
  private final CANSparkMax intakeMotor;
  private double intakeSpeed = INTAKE_SPEED_PROPORTION;
  private final ShuffleboardLayout layout;
  private final Solenoid pneumatics;

  /** Creates a new Intake. */
  public Intake() {
    intakeMotor = new CANSparkMax(INTAKE_MOTOR_ID, MotorType.kBrushless);
    layout = Shuffleboard.getTab("driveData").getLayout("Intake", BuiltInLayouts.kList).withSize(2, 2).withPosition(0, 0);
    layout.add("Intake Speed", new HSendableBuilder().addDoublePropertyInline("Intake speed", this::getIntakeSpeed, (double val) -> {setIntakeSpeed(val);}));
    pneumatics = new Solenoid(PneumaticsModuleType.CTREPCM, INTAKE_SOLENOID_ID); //FIXME set PneumaticsModuleType
  }

  public void intakeBall() {
    intakeMotor.set(INTAKE_DIRECTION*INTAKE_SPEED_PROPORTION);
  }

  public void outputBall() {
    intakeMotor.set(-INTAKE_DIRECTION*INTAKE_SPEED_PROPORTION);
  }

  public void extendIntake() {
    pneumatics.set(true);
  }

  public void retractIntake() {
    pneumatics.set(false);
  }

  public double getIntakeSpeed() {
    return intakeSpeed;
  }

  public void setIntakeSpeed(double val) {
    intakeSpeed = val;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
