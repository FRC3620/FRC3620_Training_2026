package frc.robot;

import java.util.Date;

import org.usfirst.frc3620.logger.LoggingMaster;

import dev.doglog.DogLog;
import dev.doglog.DogLogOptions;

import org.tinylog.TaggedLogger;
import org.usfirst.frc3620.GitNess;
import org.usfirst.frc3620.RobotMode;
import org.usfirst.frc3620.Utilities;

import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private TaggedLogger logger;

  static private RobotMode currentRobotMode = RobotMode.INIT, previousRobotMode;

  Date dateAtInitialization = new Date();
  DoubleEntry aEntry, bEntry, xEntry, cEntry, dEntry;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // get data logging going
    DogLog.setOptions(new DogLogOptions().withCaptureDs(false).withCaptureNt(false));
    DataLogManager.start();

    logger = LoggingMaster.getLogger(getClass());
    logger.info ("I'm alive! {}", GitNess.gitDescription());
    Utilities.logMetadataToDataLog();

    // whenever a command initializes, the function declared below will run.
    CommandScheduler.getInstance().onCommandInitialize(command ->
            logger.info("Initialized {}", command.getClass().getSimpleName()));

    // whenever a command ends, the function declared below will run.
    CommandScheduler.getInstance().onCommandFinish(command ->
            logger.info("Ended {}", command.getClass().getSimpleName()));

    // whenever a command ends, the function declared below will run.
    CommandScheduler.getInstance().onCommandInterrupt(command ->
            logger.info("Interrupted {}", command.getClass().getSimpleName()));
    
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    enableLiveWindowInTest(true);

    DriverStation.silenceJoystickConnectionWarning(true);
    aEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/a").getEntry(0.0);
    bEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/b").getEntry(0.0);
    cEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/c").getEntry(0.0);
    dEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/d").getEntry(0.0);
    xEntry = NetworkTableInstance.getDefault().getDoubleTopic("/SmartDashboard/x").getEntry(0.0);
    aEntry.set(0.0);
    bEntry.set(0.0);
    cEntry.set(0,0);
    dEntry.set(0,0);
}

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  double a = aEntry.get();
    double b = bEntry.get();
    double c = cEntry.get();
    double d = dEntry.get();
    double x = a * (b + c) * d;
    xEntry.set(x);
}

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    processRobotModeChange(RobotMode.DISABLED);
  }

  @Override
  public void disabledPeriodic() {
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    processRobotModeChange(RobotMode.AUTONOMOUS);

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    processRobotModeChange(RobotMode.TELEOP);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    processRobotModeChange(RobotMode.TEST);
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

  }

  /*
  * this routine gets called whenever we change modes
  */
  void processRobotModeChange(RobotMode newMode) {
    previousRobotMode = currentRobotMode;
    currentRobotMode = newMode;

    // if any subsystems need to know about mode changes, let
    // them know here.
    // exampleSubsystem.processRobotModeChange(newMode);
  }

  public static RobotMode getCurrentRobotMode(){
    return currentRobotMode;
  }

  public static RobotMode getPreviousRobotMode(){
    return previousRobotMode;
  }
}