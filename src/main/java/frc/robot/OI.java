// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.visionAim;
import frc.robot.commands.testShooter;

/** Add your docs here. */
public class OI {
    private static final XboxController driverController = new XboxController(0);
    // private static final XboxController manipulatorController = new XboxController(1);

    public static DoubleSupplier[] getDriveSuppliers() {
       return new DoubleSupplier[] {() -> -driverController.getLeftY(), () -> driverController.getRightX()};
    }

    public void bindButtons() {
        new JoystickButton(driverController, XboxController.Button.kB.value).whenPressed(new visionAim());
        new JoystickButton(driverController, XboxController.Button.kY.value).whenPressed(new testShooter());
    }
}
