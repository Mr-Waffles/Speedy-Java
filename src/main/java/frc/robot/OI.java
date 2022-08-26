// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Climber;

/** Add your docs here. */
public class OI {
    private static final XboxController driverController = new XboxController(0);
    private static final XboxController manipulatorController = new XboxController(1);
    private final Climber climber;

    public OI() {
        // intake = new Intake();
        climber = new Climber();
    }

    public static DoubleSupplier[] getDriveSuppliers() {
       return new DoubleSupplier[] {() -> driverController.getLeftY(), () -> driverController.getRightX()};
    }

    public void bindButtons() {
        //driver controls
        // new JoystickButton(driverController, XboxController.Button.kB.value).whenPressed(new visionAim());
        // new JoystickButton(driverController, XboxController.Button.kY.value).whenPressed(new testShooter());

        //manipulator controls
        // new JoystickButton(manipController, XboxController.Button.kB.value).whenPressed(intake::intakeBall).whenReleased(intake::stopIntake); 
        // new JoystickButton(manipController, XboxController.Button.kA.value).whenPressed(intake::outputBall).whenReleased(intake::stopIntake);
        // new JoystickButton(manipController, XboxController.Button.kRightBumper.value).whenPressed(intake::extendIntake).whenReleased(intake::retractIntake);
        new JoystickButton(manipulatorController, XboxController.Button.kX.value).whenPressed(climber::Lower).whenReleased(climber::Stop);
        new JoystickButton(manipulatorController, XboxController.Button.kY.value).whenPressed(climber::Raise).whenReleased(climber::Stop);
    }
}
