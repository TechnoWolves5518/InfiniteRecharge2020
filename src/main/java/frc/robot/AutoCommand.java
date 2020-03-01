package frc.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.DriveTrain.*;
import frc.robot.Spinsor.*;
public abstract class AutoCommand extends CommandGroup
{

    /// This is a hub for Subsystem Initializations ///

    
    public static DriveTrainSubsystem driveSubsystem;
    // public static ColorSensorSubsystem colorSensorSubsystem;
    public static SpinnerMotorSubsystem spinnerSubsystem;
    

    public static void init() 
    {
        // colorSensorSubsystem = new ColorSensorSubsystem();
        spinnerSubsystem = new SpinnerMotorSubsystem();
        driveSubsystem = new DriveTrainSubsystem();
        
    }

}