package org.firstinspires.ftc.teamcode.OpModes_Teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Arm.JogArm;
import org.firstinspires.ftc.teamcode.Commands.Auto.DetectAprilTags;
import org.firstinspires.ftc.teamcode.Commands.Auto.LogAprilTagDetect;
import org.firstinspires.ftc.teamcode.Commands.Climber.JogClimber;
import org.firstinspires.ftc.teamcode.Commands.Climber.PositionHoldClimber;
import org.firstinspires.ftc.teamcode.Commands.Drive.JogDrive;
import org.firstinspires.ftc.teamcode.Commands.Drive.JogDriveSlow;
import org.firstinspires.ftc.teamcode.Commands.Drive.TrajectoryToBackboardSimple;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ClimberSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DroneCatapultSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected Drive_Subsystem drive;

    protected PixelHandlerSubsystem phss;

    protected ArmSubsystem arm;

    protected ClimberSubsystem climber;

    protected Vision_Subsystem vss;
    Pose2d poseEstimate;
    GamepadEx driver;
    GamepadEx coDriver;
    private DroneCatapultSubsystem dcatss;

    int ctr;

    private int teleSwitch;

    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);

        drive = new Drive_Subsystem(this);

        phss = new PixelHandlerSubsystem(this);

        arm = new ArmSubsystem(this);

        climber = new ClimberSubsystem(this);

        vss = new Vision_Subsystem(this);


        dcatss = new DroneCatapultSubsystem(this);

        //DEFAULT COMMANDS

        drive.setDefaultCommand(new JogDrive(this.drive, driver));

        climber.setDefaultCommand(new PositionHoldClimber(climber));

/**
 * Driver gamepad assignmnents
 * */

        driver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setTargetInches(15)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));


        driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setTargetInches(8)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));


        driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> arm.setArmDeliverLevel(0)));


        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> phss.flipGrippersToPickup()),
                        new WaitCommand(100),
                        new InstantCommand(() -> phss.lowerGrippersToPickup()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> arm.setTargetInches(0.8))));


        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(() -> phss.toggleLeftGripper()));

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new InstantCommand(() -> phss.toggleRightGripper()));

        driver.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new InstantCommand(() -> drive.drive.toggleFieldCentric()));

        // coDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).


        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(new InstantCommand(() -> phss.flipGrippersToDeliver()));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(new InstantCommand(() -> climber.climberToLiftPosition()));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                        new JogDriveSlow(drive, driver))
                .whenPressed(new InstantCommand(() -> phss.toWideOpen()));

        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(() -> dcatss.releaseCatapult()));


        // driver.getGamepadButton(GamepadKeys.Button.BACK)


        driver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON).whenPressed(
                new ParallelRaceGroup(
                        new DetectAprilTags(this, vss, false),
                        new LogAprilTagDetect(drive, this)));

        //
        // driver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)

        // if (coDriver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) == 1)


        //   if (coDriver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) == 1)


        coDriver.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setArmDeliverLevel(10)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));


        coDriver.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> arm.setArmDeliverLevel(0)));


        coDriver.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setArmDeliverLevel(0)),
                        new InstantCommand(() -> phss.flipGrippersToPickup()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.lowerGrippersToPickup())));

        coDriver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> arm.setArmDeliverLevel(20)),
                        new InstantCommand(() -> phss.raiseGrippersToDeliver()),
                        new WaitCommand(1000),
                        new InstantCommand(() -> phss.flipGrippersToDeliver())));

        // coDriver.getGamepadButton(GamepadKeys.Button.A).whenPressed(


//        coDriver.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> climber.climberToClearBar()));
//
//        coDriver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(() -> climber.climberToLiftPosition()));


        coDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new TrajectoryToBackboardSimple(drive, this));


        // coDriver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)


        // coDriver.getGamepadButton(GamepadKeys.Button.BACK)


        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(new InstantCommand(() -> teleSwitch++));

        // coDriver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).


        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new JogClimber(climber, coDriver));


        coDriver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new JogArm(arm, coDriver));

        coDriver.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new InstantCommand(() -> drive.drive.toggleFieldCentric()));

        //coDriver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(


        // coDriver.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
        //
        // coDriver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)

    }


    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

            run();

            checkTriggers();

            showTelemetry();


        }
        reset();

    }

    void checkTriggers() {

        if (driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) == 1) {
            new JogDriveSlow(drive, driver).execute();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) == 1)
            new InstantCommand(() -> phss.closeBothGrippers()).initialize();

        telemetry.update();
    }


    public void showTelemetry() {

        poseEstimate = drive.drive.getPoseEstimate();
        if (teleSwitch > 4) teleSwitch = 0;
        if (teleSwitch < 0) teleSwitch = 4;


        switch (teleSwitch) {

            case 0:
                drive.showTelemetry(telemetry);
                break;
            case 1:
                drive.drive.showTelemetry(telemetry);
                break;
            case 2:
                arm.showTelemetry(telemetry);
                break;
            case 3:
                phss.showTelemetry(telemetry);
                break;
            case 4:
                climber.showTelemetry(telemetry);
                break;
            default:
                break;

        }
    }
}

