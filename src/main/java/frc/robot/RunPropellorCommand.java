// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

/** Add your docs here. */
public class RunPropellorCommand extends Command {
    double savedPower;
    Timer timer;

    public RunPropellorCommand(double power) {
        savedPower = power;

        timer = new Timer();

        addRequirements(RobotContainer.propellorSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        double power = savedPower;
        if (timer.hasElapsed(2.0)) {
            power = savedPower / 3;
        } else if (timer.hasElapsed(1.0)) {
            power = savedPower / 2;
        } else {
            power = savedPower;
        }
        RobotContainer.propellorSubsystem.spinPropellor(power);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        RobotContainer.propellorSubsystem.spinPropellor(0);
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(5.0);
    }

}