// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utilities.HSendableBuilder;

import static frc.robot.RobotContainer.drivetrain;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class visionAim extends CommandBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  private double kP = 0.03;
  private double kI = 0.00;
  private double kD = 0.00;

  private static PIDController pid = null;
  private final ShuffleboardLayout layout;

  // private final NetworkTableEntry PWidget;
  // private final NetworkTableEntry IWidget;
  // private final NetworkTableEntry DWidget;

  /** Creates a new VisionAim. */
  public visionAim() {
    // Use addRequirements() here to declare subsystem dependencies.
    layout = Shuffleboard.getTab("driveData").getLayout("Intake", BuiltInLayouts.kList).withSize(2, 4).withPosition(2, 0);
    layout.add("P", new HSendableBuilder().addDoublePropertyInline("kP", this::getKP, (double val) -> {setKP(val);}));
    layout.add("I", new HSendableBuilder().addDoublePropertyInline("kI", this::getKI, (double val) -> {setKI(val);}));
    layout.add("D", new HSendableBuilder().addDoublePropertyInline("kD", this::getKD, (double val) -> {setKD(val);}));
    layout.addNumber("Limelight X", () -> tx.getDouble(0.0));



    // PWidget = Shuffleboard.getTab("driveData").add("P", kP).getEntry();
    // IWidget = Shuffleboard.getTab("driveData").add("I", kI).getEntry();
    // DWidget = Shuffleboard.getTab("driveData").add("D", kD).getEntry();

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // PWidget.setNumber(kP);
    // IWidget.setNumber(kI);
    // DWidget.setNumber(kD);
    pid = new PIDController(kP, kI, kD);
    pid.setTolerance(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = tx.getDouble(0.0);
    double tSpeed = -(pid.calculate(x));

    drivetrain.drive(0, tSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }

  //getters and setters
  public double getKP() {
    return this.kP;
  }

  public void setKP(double kP) {
    this.kP = kP;
  }

  public double getKI() {
    return this.kI;
  }

  public void setKI(double kI) {
    this.kI = kI;
  }

  public double getKD() {
    return this.kD;
  }

  public void setKD(double kD) {
    this.kD = kD;
  }
}
