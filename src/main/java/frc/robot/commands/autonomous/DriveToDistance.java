package frc.robot.commands.autonomous;

import static frc.robot.RobotContainer.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
public class DriveToDistance extends CommandBase {
  private double desiredSpeed;
  private double desiredDistance;
  private boolean isFinished = false;

  public DriveToDistance(double speed, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    desiredSpeed = speed;
    desiredDistance = distance;
  }

  // Called repeatedly when this Command is scheduled to run
  public void Execute() {
      drivetrain.driveUsingSpeeds(desiredSpeed, desiredSpeed);
      isFinished = Math.abs(desiredDistance) < Math.abs(drivetrain.getEncoderRotations()[0]);
  }

  // Called once the command ends or is interrupted.
  public void End(Boolean interrupted) {
      drivetrain.driveUsingSpeeds(0,0);
  }

  // Returns true when the command should end.
  public boolean IsFinished() {
    return isFinished;
  }
}
