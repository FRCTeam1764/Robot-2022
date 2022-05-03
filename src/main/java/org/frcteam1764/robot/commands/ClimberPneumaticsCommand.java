// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.frcteam1764.robot.commands;

import org.frcteam1764.robot.state.ClimberState;
import org.frcteam1764.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberPneumaticsCommand extends CommandBase {
  /** Creates a new ConveyorCommand. */
 private Climber climber;
 private ClimberState climberState;
 private boolean finishedState;

  public ClimberPneumaticsCommand(Climber climber, ClimberState climberState, boolean finishedState) {
    this.climber = climber;
    this.climberState = climberState;
    this.finishedState = finishedState;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(finishedState){
      climber.pneumaticsDeploy();
    }
    else {
      climber.pneumaticsWithdraw();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (climberState.isClimberPistonsDeployed() && finishedState) || (!climberState.isClimberPistonsDeployed() && !finishedState);
  }
}
