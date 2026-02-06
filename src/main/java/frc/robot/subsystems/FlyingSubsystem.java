// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyingSubsystem extends SubsystemBase {
  
  Servo motor;

  /** Creates a new FlyingSubsystem. */
  public FlyingSubsystem() {
    motor = new Servo(1);
  }
;
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void goFast() {
    motor.set(1.0); 
  }

  public void dougsSillyMethod() {
    //System.out.println(i);
    int i = 0;
    System.out.println(i);
    x = 0;
  }

  float x;
}
