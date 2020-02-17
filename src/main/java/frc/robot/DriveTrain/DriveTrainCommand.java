package frc.robot.DriveTrain;

import frc.robot.OI;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;


public class DriveTrainCommand extends CommandBase
{
    public DriveTrainCommand() 
    {
        /// MAKES THIS FILE REQUIRE driveSubsystem ///
        requires(driveSubsystem);
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
           // System.out.println("Drive Train Command Init");
        }
    }

    /// MAKES A CONTROLLER CALLED "DRIVER" THAT IS EQUAL TO A CONTROLLER DEFINED IN OI FILE ///
    private static XboxController driver = OI.driverController;

    /// Null speed variables ///
    public double forwardSpeedLeft, forwardSpeedRight;
    public boolean speedModToggle = true;
    public boolean slowModeToggle = false;
    

    // other variables
    public boolean autoMode;
    public boolean slowMode;
    public boolean cubicSafety;
    public boolean safetyMod;
    
    @Override
    protected void execute()
    {

        /// SETS SPEED VARIABLES EQUAL TO STICK VALUES ///
        forwardSpeedLeft = driver.getRawAxis(OI.leftStickY);
        forwardSpeedRight = driver.getRawAxis(OI.rightStickY);
        slowMode = driver.getRawButton(OI.rightBumper);
        safetyMod = driver.getRawButton(OI.rightBumper);
        cubicSafety = driver.getRawButton(OI.leftTrigger);


        checkBumpers();
        
        motorDriveCode();
    }

    /// Checks for bumpers, which manually disable safeties ///
    public void checkBumpers()
    {
        /// If both bumpers are pressed, don't let either safety disable /// 
        if ((cubicSafety == true) && (safetyMod == true))
            {
                System.out.println("Both Bumpers Pressed, Safeties enabled");
            }
        else
        {
            /// If left trigger pressed, disable speed modifiers ///
            if (safetyMod == true)
                {
                    speedModToggle = false;
                }
            /// If left bumper pressed, disable cubic safety ///
            if (cubicSafety == true)
                {
                    RobotMap.driveTrainSafety = !RobotMap.driveTrainSafety;
                }}
       }

      

    /// Main driver code for motors ///
    public void motorDriveCode() 
    {
        if (autoMode == true)
        {
            driveSubsystem.setMotors(forwardSpeedLeft, "left", speedModToggle,false,false);
            driveSubsystem.setMotors(forwardSpeedRight, "right", speedModToggle,false,false);

            if (RobotMap.driveDebug)
            {
                driveSubsystem.setMotors(0, "left", speedModToggle,false,false);
                driveSubsystem.setMotors(0, "right", speedModToggle,false,false);
                System.out.println("Autonomous code not working");
            }
        }

        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedLeft) > RobotMap.deadzone) 
        {
            driveSubsystem.setMotors(forwardSpeedLeft, "left", speedModToggle, cubicSafety, slowMode);
        }
        else
        {
            /// DEBUG CODE ///
            if (RobotMap.driveDebug)
            {
                driveSubsystem.setMotors(0, "left", speedModToggle, cubicSafety, slowMode);
                //System.out.println("Left Stick not above Deadzone");
            }
        }
        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedRight) > RobotMap.deadzone)
        {
            driveSubsystem.setMotors(forwardSpeedRight, "right", speedModToggle, cubicSafety, slowMode);
        }
        else
        {
            /// DEBUG CODE ///
            if (RobotMap.driveDebug)
            {
                driveSubsystem.setMotors(0, "right", speedModToggle, cubicSafety, slowMode);
               // System.out.println("Right Stick not above Deadzone");
            }
        }
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
          //  System.out.println("DriveTrainCommand Driver Code");
        }
        
    }


    @Override
    protected boolean isFinished() 
    {
        return false;
    }

    @Override
    protected void end() 
    {
        
    }
    
}