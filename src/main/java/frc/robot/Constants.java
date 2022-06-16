// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
    public static final int LEFT_PRIMARY_ID = 2;
    public static final int RIGHT_PRIMARY_ID = 1;
    public static final int LEFT_FOLLOWER_ID = 4;
    public static final int RIGHT_FOLLOWER_ID = 3;
    
    public static final int LEFT_CLIMBER_ID = 8;
    public static final int RIGHT_CLIMBER_ID = 7;

    public static final int PCM_CAN_ID = 15;
    public static final int CLIMBER_SOLENOID_ID = 4;
    public static final int ARM_SOLENOID_ID = 9;

    public static final int BOTTOM_OPTICAL_ID = 0;
    public static final int MIDDLE_OPTICAL_ID = 2;
    public static final int TOP_OPTICAL_ID = 1;

    public static final double RAMP_RATE = 0.2; // FIXME confirm

    public static final double ROTATION_SPEED_PROPORTION = 0.75;
    public static final double SPEED_PROPORTION = 1;
}
