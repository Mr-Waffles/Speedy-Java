// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;

/** Add your docs here. */
public class HSendableBuilder extends SendableBuilderImpl {

    public HSendableBuilder addDoublePropertyInline(String key, DoubleSupplier getter, DoubleConsumer setter) {
        super.addDoubleProperty(key, getter, setter);
        return this;
    }
}
