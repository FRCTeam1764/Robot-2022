// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.frcteam1764.robot.commands;

import org.frcteam1764.robot.state.ClimberState;
import org.frcteam1764.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberCommand extends CommandBase {
  /** Creates a new ConveyorCommand. */
 private Climber climber;
 private double climberSpeed;
 private ClimberState climberState;

  public ClimberCommand(Climber climber, double climberSpeed, ClimberState climberState) {
    this.climber = climber;
    this.climberSpeed = climberSpeed;
    this.climberState = climberState;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((!climberState.isClimberPistonsDeployed() && climberSpeed > 0 && climber.getMasterEncoder() < 300000) || climberState.isClimberPistonsDeployed() || climberSpeed < 0) {
      climber.climberOn(climberSpeed);
    }
    else {
      climber.climberOn(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.climberOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
