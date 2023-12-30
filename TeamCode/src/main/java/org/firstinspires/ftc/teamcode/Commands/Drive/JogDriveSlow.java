package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDriveSlow extends CommandBase {
    private Drive_Subsystem drive;

    private GamepadEx gamepad;

    private double startRadians;

    private double forwardRatio = .3;
    private double strafeRatio = .3;
    private double rotRatio = .3;


    public JogDriveSlow(Drive_Subsystem drive, GamepadEx gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
        addRequirements(this.drive);

    }


    @Override
    public void initialize() {
        double gyro_radians = startRadians - drive.drive.getRawExternalHeading();
    }


    @Override
    public void execute() {

        forwardRatio=.2;
        strafeRatio=.2;
        rotRatio=.2;

        drive.slowMode++;

        if (!drive.drive.fieldCentric) {

            double forward = this.gamepad.getLeftY() * forwardRatio;
            double strafe = this.gamepad.getLeftX() * strafeRatio;
            double rx = this.gamepad.getRightX() * rotRatio;

            drive.drive.jog(forward, strafe, rx);

        }

        if (drive.drive.fieldCentric) {

            double strafe = -this.gamepad.getLeftY() * strafeRatio; /* Invert stick Y axis */
            double forward = this.gamepad.getLeftX() * forwardRatio;
            double rcw = this.gamepad.getRightX() * rotRatio;


            if (!ActiveMotionValues.getRedAlliance()) {

                strafe = this.gamepad.getLeftY() * strafe; /* Invert stick Y axis */
                forward = -this.gamepad.getLeftX() * forward;
                rcw = this.gamepad.getRightX() * rotRatio;
            }




            /* Adjust Joystick X/Y inputs by navX MXP yaw angle */

            double gyro_radians = startRadians - drive.drive.getRawExternalHeading();


            double temp = strafe * Math.sin(gyro_radians) + forward * (float) Math.cos(gyro_radians);

            strafe = strafe * Math.cos(gyro_radians) - forward * Math.sin(gyro_radians);

            forward = temp;

            drive.drive.jog(forward, strafe, rcw);
        }

    }

    @Override
    public void end(boolean interrupted) {
        drive.slowMode = 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
