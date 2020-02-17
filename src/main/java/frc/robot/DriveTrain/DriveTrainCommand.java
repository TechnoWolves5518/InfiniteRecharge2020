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

    // Autonomous variable
    public boolean autoMode = false;
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
        cubicSafety = driver.getRawButton(OI.leftTrigger);
        safetyMod = driver.getRawButton(OI.leftBumper);

        checkBumpers();
        
        motorDriveCode();
    }

    /// Checks for bumpers, which manually disable safeties ///
    public void checkBumpers()
    {            
        
        /// If both left bumper and trigger are pressed, don't let either safety disable /// 
        if ((cubicSafety == true) && (safetyMod == true))
            {
<<<<<<< HEAD
              //  System.out.println("insert debug thing here");
=======
                System.out.println("Both Bumpers Pressed, Safeties enabled");
>>>>>>> Changed small things, formattting
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
            driveSubsystem.setMotors(forwardSpeedLeft, "left", cubicSafety, speedModToggle, slowMode) ;
            driveSubsystem.setMotors(forwardSpeedRight, "right", cubicSafety, speedModToggle, slowMode);

            if (RobotMap.driveDebug)
            {
                driveSubsystem.setMotors(0, "left", cubicSafety, speedModToggle, slowMode);
                //System.out.println("Autonomous code not working");
            }
        }

        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedLeft) > RobotMap.deadzone) 
        {
            driveSubsystem.setMotors(forwardSpeedLeft, "left", cubicSafety, speedModToggle, autoMode);
        }
        else
        {
            /// DEBUG CODE ///
            if (RobotMap.driveDebug)
            {
<<<<<<< HEAD
                driveSubsystem.setMotors(0, "left", speedModToggle);
=======
                driveSubsystem.setMotors(0, "left", cubicSafety, speedModToggle, slowMode);
>>>>>>> Code update 2_17_2020
                //System.out.println("Left Stick not above Deadzone");
            }
        }
        /// CHECKS IF STICK IS BEYOND DEADZONE. SETS MOTOR IF SO, PRINTS ERROR IF NOT ///
        if (Math.abs(forwardSpeedRight) > RobotMap.deadzone)
        {
            driveSubsystem.setMotors(forwardSpeedRight, "right", cubicSafety, speedModToggle, slowMode);
        }
        else
        {
            /// DEBUG CODE ///
            if (RobotMap.driveDebug)
            {
<<<<<<< HEAD
                driveSubsystem.setMotors(0, "right", speedModToggle);
=======
                driveSubsystem.setMotors(0, "right", cubicSafety, speedModToggle, slowMode);
>>>>>>> Code update 2_17_2020
               // System.out.println("Right Stick not above Deadzone");
            }
        }
        /// DEBUG CODE ///
        if (RobotMap.debug)
        {
<<<<<<< HEAD
          //  System.out.println("DriveTrainCommand Driver Code");
=======
            //System.out.println("DriveTrainCommand Driver Code");
>>>>>>> Code update 2_17_2020
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