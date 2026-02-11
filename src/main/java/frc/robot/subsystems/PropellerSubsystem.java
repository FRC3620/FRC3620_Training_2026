// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PropellerSubsystem extends SubsystemBase {
  Spark propellerMotor;
  /** Creates a new PropellerSubsystem. */
  public PropellerSubsystem() {
    propellerMotor = new Spark(1);
  }
  public void spinPropeller(double power) {
propellerMotor.set(power);
SmartDashboard.putNumber("propeller.power",power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
