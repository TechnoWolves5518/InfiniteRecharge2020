package frc.robot.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class DriveTrainSubsystem extends Subsystem
{

    //// TEST COMMIT TEXT ////

    /// Motor Definitions ///
        /// Left Side ///
    public VictorSPX leftMotorMaster = new VictorSPX(RobotMap.leftMotorMaster);
    public VictorSPX leftMotorSlave = new VictorSPX(RobotMap.leftMotorSlave);
        /// Right Side ///
    public VictorSPX rightMotorMaster = new VictorSPX(RobotMap.rightMotorMaster);
    public VictorSPX rightMotorSlave = new VictorSPX(RobotMap.rightMotorSlave);


    /*//switch statement arguments
    public int x = 0;
    public static int cubeNumber;
    public static int safetySpeedNumber;
    public static int slowModeNumber;*/
    
    /// This function sets motor speeds ///
    /// It contains nested conditionals; one that checks for the safety toggle, and one that checks which side is being set ///
    /// There is a debug at the end /// 
    public void setMotors(final double speed, final String side, final boolean speedMod, final boolean cubicSafety, final boolean slowMode) 
    {
       
        if (side == "left")
        {
            System.out.println("left side activated");
            leftMotorSlave.set(ControlMode.PercentOutput, configSpeed(-speed, speedMod, cubicSafety, slowMode, "left"));
            leftMotorMaster.set(ControlMode.PercentOutput, configSpeed(-speed, speedMod, cubicSafety, slowMode, "left"));
        }
        if (side == "right")
        {
            System.out.println("right side activated");
            rightMotorSlave.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, cubicSafety, slowMode, "right"));
            rightMotorMaster.set(ControlMode.PercentOutput, configSpeed(speed, speedMod, cubicSafety, slowMode, "right"));

        }
       

       /// DEBUG CODE ///
       if (RobotMap.driveDebug)
       {
          // System.out.println("Side : " + side + " \t\t Speed : " + speed);
       }
    }

    /// This function returns a double based on the values of two safety variables ///
    public static double configSpeed(final double speed, final boolean speedMod, final boolean cubicSafety, final boolean slowMode, final String side)
    {

        final double returnVar;
        if (slowMode == true)
        {
            returnVar = speed * RobotMap.driveSlowMode;
            System.out.println("slow mode activated for " + side);
        }    
        else
        {
            /// If cubic safety AND speed modifiers enabled... ///
            if ((cubicSafety) && (speedMod))
            {
            ///Return speed**3 * speed modifier ///
            returnVar = ((Math.pow(speed, 3)) * RobotMap.driveSafetySpeedMod);
            }
            /// If cubic safety enabled but NOT speed modifiers... ///
            else if ((cubicSafety) && (!speedMod))
            {
                /// Return speed**3 ///
                returnVar = Math.pow(speed, 3);
            }
            /// If speed modifiers enabled but NOT cubic safety... ///
            else if ((!cubicSafety) && (speedMod))
            {
                /// Return speed * speed modifier ///
                returnVar = speed * (RobotMap.driveSafetySpeedMod);
            }
            /// If neither cubic safety or speed modifiers enabled... ///
            else if ((!cubicSafety) && (!speedMod))
            {
                /// Return raw speed ///asdkljhsdfa
                returnVar = speed;
                System.out.println(side + " " + speed);
            }
            /// IF NONE OF THE ABOVE ARE TRUE (somehow...) ///
            else
            {
                /// DEBUG CODE ///
                if (RobotMap.driveDebug)
                {
                // System.out.println("Error in configSpeed");
                }
                /// Return 0 ///
                return 0;
            }
        }
        
         /* if (safetyCube)
        {
            cubeNumber = 1;
        }
        if (speedMod)
        {
            safetySpeedNumber = 2;
        }
        if (drive)

        switch (x) {
            case 1:
            // Only Cubic Trigger pressed
            /// Return speed**3 
            returnVar = Math.pow(speed, 3);
            break;
            case 2:
            // Only Left Bumper speed mod pressed
            /// Return speed * speed modifier ///
            returnVar = speed * (RobotMap.driveSafetySpeedMod);
            break;
            case 3:
            // Only Right Bumper pressed
            // Slow mode for wheel turning
            returnVar = speed * (RobotMap.driveSlowMode);
            break;
            case 4:
            // Both left trigger and left bumper are pressed
            /// Return raw speed ///
            returnVar = speed;
            break;
            case 5:
            // If cubic mod and slow mode mod are pressed
            /// Return speed**3 * slow mode speed modifier ///
            returnVar = ((Math.pow(speed, 3)) * RobotMap.driveSlowMode);
            break;
            case 6:
            // lefft and right bumper are pressed
            //use slow mode, ignore speed mod
            returnVar = speed * (RobotMap.driveSlowMode);
            break;
            case 7:
            // trigger and both bumpers are pressed
            // return slow mode
            returnVar = speed * (RobotMap.driveSlowMode);
            break;
            default:
            // use speed mod and cubic safety, slow mode not activated
            /// Return speed**3 * speed modifier ///
            returnVar = ((Math.pow(speed, 3)) * RobotMap.driveSafetySpeedMod);
            break;
        } */

        /// Final return statement ///
        if (RobotMap.driveDebug)
        {
            //System.out.println("Cubic Enabled : " + safetyCube + "\n Cap Enabled : " + speedMod + "\n Speed Input : " + speed + "\n Speed Output " + returnVar);
        }
        return returnVar;
    }

    @Override
    protected void initDefaultCommand() 
    {
        setDefaultCommand(new DriveTrainCommand());
    }


}