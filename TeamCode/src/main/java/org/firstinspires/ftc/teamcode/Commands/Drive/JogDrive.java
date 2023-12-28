package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDrive extends CommandBase {
    private Drive_Subsystem drive;

    private GamepadEx gamepad;

    private double startRadians;

    public JogDrive(Drive_Subsystem drive, GamepadEx gamepad) {
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

        if (!drive.drive.fieldCentric) {

            double y = this.gamepad.getLeftY();
            double x = this.gamepad.getLeftX();
            double rx = this.gamepad.getRightX();

            drive.drive.jog(y, x, rx);

        }

        if (drive.drive.fieldCentric) {





                double strafe = -this.gamepad.getLeftY(); /* Invert stick Y axis */
                double forward = this.gamepad.getLeftX();
                double rcw = this.gamepad.getRightX();


            if(!ActiveMotionValues.getRedAlliance()) {

                 strafe = this.gamepad.getLeftY(); /* Invert stick Y axis */
                forward = -this.gamepad.getLeftX();
                 rcw = this.gamepad.getRightX();
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

    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
