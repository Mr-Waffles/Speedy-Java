// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utilities.PivotState;
import frc.robot.RobotContainer;

public class PivotToggle extends CommandBase {
  public PivotToggle() {}

  @Override
  public void initialize() {
    if (RobotContainer.climber.pivotPosition == PivotState.Down) {
        RobotContainer.climber.PivotUp();
    }
    else {
        RobotContainer.climber.PivotDown();
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}