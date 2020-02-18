/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.lang.Thread;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.DriveTrain.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  Command autonomousCommand;

  public static OI oi;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {
    
    CommandBase.init();
    oi = new OI();
    //autonomousCommand = new AutoCommand();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() 
  {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 


  /////need to change this to sete the motor, not just pulse powered
  {
    switch (m_autoSelected) {
      case kCustomAuto:
      final DriveTrainCommand auto0 = new DriveTrainCommand();
      auto0.autoMode = true;
      auto0.forwardSpeedLeft = .5;
      auto0.forwardSpeedRight = .5;
      auto0.motorDriveCode();
      // Hold motors at .5 power for 2 seconds then stop afterwards
      auto0.forwardSpeedLeft = 0.0;
      auto0.forwardSpeedRight = 0.0;
      auto0.motorDriveCode();
      auto0.autoMode = false;
      System.out.println("auto mode enabled, kcuustom");
      auto0.close();
      break;
    case kDefaultAuto:
      final DriveTrainCommand auto = new DriveTrainCommand();
      auto.autoMode = true;
      auto.forwardSpeedLeft = .5;
      auto.forwardSpeedRight = .5;
      auto.motorDriveCode();
      // Hold motors at .5 power for 2 seconds then stop afterwards
      auto.forwardSpeedLeft = 0.0;
      auto.forwardSpeedRight = 0.0;
      auto.motorDriveCode();
      auto.autoMode = false;
      // System.out.println("auto mode enabled, kdefault");
      auto.close();
      break;
    default:
      // Put default auto code here
      // Move the robot forward and stop after leaving line but before hitting middle
      // area
      try {
        final DriveTrainCommand auto1 = new DriveTrainCommand();
        auto1.autoMode = true;
        auto1.forwardSpeedLeft = .5;
        auto1.forwardSpeedRight = .5;
        auto1.motorDriveCode();
        // Hold motors at .5 power for 2 seconds then stop afterwards
        Thread.sleep(2000);
        auto1.forwardSpeedLeft = 0.0;
        auto1.forwardSpeedRight = 0.0;
        auto1.motorDriveCode();
        auto1.autoMode = false;
        System.out.println("auto mode enabled, default default");
        auto1.close();
      } catch (final InterruptedException e)
        {
          e.printStackTrace();
        }
      }  
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    // ini drive train
   //dt = new DriveTrainSubsystem();
  
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() 
  {
      
  }
}
