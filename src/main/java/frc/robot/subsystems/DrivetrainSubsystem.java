// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {

  public double speed;

  public MotorController motorControllerRightBack;
  public MotorController motorControllerRightFront;
  public MotorController motorControllerLeftFront;
  public MotorController motorControllerLeftBack;

  private MotorControllerGroup leftMotors;
  private MotorControllerGroup rightMotors;

  public DifferentialDrive tankDrive;
  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {

    speed = 0.68;

    motorControllerLeftFront = new Spark(0);
    motorControllerLeftBack = new Spark(1);
    motorControllerRightFront = new Spark(2);
    motorControllerRightBack = new Spark(3);

    this.leftMotors = new MotorControllerGroup(
      this.motorControllerLeftFront,
      this.motorControllerLeftBack
    );
    this.rightMotors = new MotorControllerGroup(
      this.motorControllerRightFront,
      this.motorControllerRightBack
    );
   
    tankDrive = new DifferentialDrive(leftMotors, rightMotors);

    rightMotors.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double leftSpeed, double rightSpeed) {

    leftSpeed = leftSpeed * speed;
    rightSpeed = rightSpeed * speed;

   SmartDashboard.putNumber("leftSpeed", leftSpeed);
   SmartDashboard.putNumber("RightSpeed", rightSpeed);

  tankDrive.tankDrive(leftSpeed, rightSpeed);

  }
}
