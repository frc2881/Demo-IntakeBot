// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import java.util.function.DoubleSupplier;

public class FeedIntake extends CommandBase {
  private Intake m_intake;
  private final DoubleSupplier m_triggerLeft;
  private final DoubleSupplier m_triggerRight;

  public FeedIntake(Intake intake, DoubleSupplier triggerLeft, DoubleSupplier triggerRight) {
    m_intake = intake;
    m_triggerLeft = triggerLeft;
    m_triggerRight = triggerRight;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed;
    double triggerLeft = m_triggerLeft.getAsDouble();
    double triggerRight = m_triggerRight.getAsDouble();
    if(triggerLeft != 0 && triggerRight == 0){
        speed = triggerLeft;

    }else if(triggerRight != 0 && triggerLeft == 0){
        speed = -triggerRight;

    }else{
        speed = 0;
    }
    m_intake.intakeRun(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.intakeRun(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
