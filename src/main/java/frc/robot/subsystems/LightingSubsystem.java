// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Lighting.Colors;

public class LightingSubsystem extends SubsystemBase {
  public Spark lighting;

  public final SendableChooser<Constants.Lighting.Colors> lighting_chooser = new SendableChooser<>();

  /** Creates a new LightingSubsys
   * tem. */
  public LightingSubsystem() {
    lighting = new Spark(Constants.Lighting.LIGHTING_PORT);

    lighting.set(Constants.Lighting.STARTING_COLOR.getColorValue());

    lighting_chooser.setDefaultOption(Constants.Lighting.STARTING_COLOR.getColorName(), Constants.Lighting.STARTING_COLOR);
  
    for (Colors c : Colors.values()) {
      lighting_chooser.addOption(c.getColorName(), c);
    }

    SmartDashboard.putData("Alliance", lighting_chooser);
  }

  public void blink(){
    Colors selectedColor = lighting_chooser.getSelected();

    lighting.set(selectedColor.getColorValue());
    lighting.set(0.99);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Colors selectedColor = lighting_chooser.getSelected();

    lighting.set(selectedColor.getColorValue());
  }
}
