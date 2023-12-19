package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class JogDrivePickup extends CommandBase {
    private Drive_Subsystem drive;

    private GamepadEx gamepad;
    private double startRadians;


    public JogDrivePickup(Drive_Subsystem drive, GamepadEx gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
        addRequirements(this.drive);

    }


    @Override
    public void initialize() {
        startRadians = drive.drive.getRawExternalHeading();
    }

    @Override
    public void execute() {
        double yDivide = 4;
        double xDivide = 4;
        double rDivide = 4;

        if (!drive.drive.fieldCentric) {

            double y = this.gamepad.getLeftY() / yDivide;
            double x = this.gamepad.getLeftX() / xDivide;
            double rx = this.gamepad.getRightX() / rDivide;

            drive.drive.jog(y, x, rx);

        }

        if (drive.drive.fieldCentric) {

            double forward = this.gamepad.getLeftY() / yDivide; /* Invert stick Y axis */
            double strafe = this.gamepad.getLeftX() / xDivide;
            double rcw = this.gamepad.getRightX() / rDivide;

            /* Adjust Joystick X/Y inputs by navX MXP yaw angle */

            double gyro_radians = startRadians - drive.drive.getRawExternalHeading();

            //     new drive  = strafe * sin(heading) + drive * cos(heading)
            //    new strafe = strafe * cos(heading) - drive * sin(heading)


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
