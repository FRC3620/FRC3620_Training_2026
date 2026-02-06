// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

/** Add your docs here. */
public class RunPropellorCommand extends Command{

public RunPropellorCommand()
{
    addRequirements(RobotContainer.propellorSubsystem);
}

@Override
public void initialize() {}

@Override
public void execute() {
    RobotContainer.propellorSubsystem.spinPropellor(0.5);
}

@Override
public void end(boolean interrupted) {
    RobotContainer.propellorSubsystem.spinPropellor(0);
}
}