// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {

  public double speed;

  // Updated to modern SparkMax objects
  public final SparkMax motorControllerLeftFront;
  public final SparkMax motorControllerLeftBack;
  public final SparkMax motorControllerRightFront;
  public final SparkMax motorControllerRightBack;

  public DifferentialDrive tankDrive;

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {

    speed = 1.00;

    // Initialize SparkMax controllers using CAN IDs (0, 1, 2, 3) and set to
    // Brushless for NEOs
    motorControllerLeftFront = new SparkMax(1, MotorType.kBrushless);
    motorControllerLeftBack = new SparkMax(2, MotorType.kBrushless);
    motorControllerRightFront = new SparkMax(3, MotorType.kBrushless);
    motorControllerRightBack = new SparkMax(4, MotorType.kBrushless);

    // Create configuration objects
    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig leftBackConfig = new SparkMaxConfig();
    SparkMaxConfig rightFrontConfig = new SparkMaxConfig();
    SparkMaxConfig rightBackConfig = new SparkMaxConfig();

    // Set common settings (Brake mode and current limits protect your NEOs)
    globalConfig
        .smartCurrentLimit(50)
        .idleMode(IdleMode.kBrake);

    // Configure left follower to follow left leader
    leftBackConfig
        .apply(globalConfig)
        .follow(motorControllerLeftFront);

    // Configure right leader to be inverted (replaces rightMotors.setInverted)
    rightFrontConfig
        .apply(globalConfig)
        .inverted(true);

    // Configure right follower to follow right leader
    rightBackConfig
        .apply(globalConfig)
        .follow(motorControllerRightFront);

    // Apply the configurations to the physical controllers and save to flash memory
    motorControllerLeftFront.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorControllerLeftBack.configure(leftBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorControllerRightFront.configure(rightFrontConfig, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    motorControllerRightBack.configure(rightBackConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // DifferentialDrive only needs the two leader motors now!
    // The followers will mirror their leaders automatically.
    tankDrive = new DifferentialDrive(motorControllerLeftFront, motorControllerRightFront);
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

    // DifferentialDrive handles sending the speed commands safely
    tankDrive.tankDrive(leftSpeed, rightSpeed);
  }
}
