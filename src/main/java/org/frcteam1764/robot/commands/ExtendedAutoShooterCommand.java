package org.frcteam1764.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import org.frcteam1764.robot.state.ShooterState;
import org.frcteam1764.robot.subsystems.Shooter;
import org.frcteam1764.robot.subsystems.ShooterTopRoller;

public class ExtendedAutoShooterCommand extends CommandBase {
  
  Shooter shooter;
  ShooterTopRoller shooterTopRoller;
  ShooterState shooterState;
  double shooterTopRollerSpeed;
  int initialShotCount;
  boolean ballIsPresent;
  double shooterRatio;

  public ExtendedAutoShooterCommand(Shooter shooter, ShooterTopRoller shooterTopRoller,
   double shooterTopRollerSpeed, ShooterState shooterState, int initialShotCount) {
    this.shooter = shooter;
    this.shooterTopRoller = shooterTopRoller;
    this.shooterState = shooterState;
    this.shooterTopRollerSpeed = shooterTopRollerSpeed;
    this.initialShotCount = initialShotCount;
    this.shooterRatio = 4;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooterState.setShotCount(initialShotCount);
    shooter.setShooterVelocity(shooterTopRollerSpeed / shooterRatio /60*2048*0.1);
    shooterTopRoller.setShooterTopRollerVelocity(shooterTopRollerSpeed /60*2048*0.1);
    shooterState.setAssignedVelocity(shooterTopRollerSpeed  / shooterRatio /60*2048*0.1);
    shooterState.setTopRollerAssignedVelocity(shooterTopRollerSpeed /60*2048*0.1);
    shooterState.clearTimer();
    shooter.shoot();
    shooterTopRoller.shoot();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // shooter.stopShooter();
    // shooterTopRoller.stopShooter();
    shooterState.clearTimer();
    // shooterState.setAssignedVelocity(0);
    // shooterState.setTopRollerAssignedVelocity(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (shooterState.getShotCount() == 2 || shooterState.getTimer() > 200);
  }
}