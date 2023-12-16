package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class DroneCatapultSubsystem extends SubsystemBase {


    public Servo catapult;

    public MotorEx catapultMotor;

    private final CommandOpMode myOpMode;
    private FtcDashboard dashboard;
    private Telemetry telemetry;


    private final double motorpower = Constants.CatapultConstants.CATAPULT_MOTOR_POWER;

    public DroneCatapultSubsystem(CommandOpMode opMode) {
        myOpMode = opMode;

        catapult = myOpMode.hardwareMap.get(Servo.class, "catapult");

        catapult.setDirection(Servo.Direction.FORWARD);

        catapultMotor = new MotorEx(myOpMode.hardwareMap, "climber motor", Motor.GoBILDA.RPM_1150);


        catapultMotor.setInverted(false);

        catapultMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

    }

    @Override

    public void periodic() {

    }


    public void setCatapultPosition(double position) {
        catapult.setPosition(position);
    }

    public void lockCatapult() {
        setCatapultPosition(Constants.CatapultConstants.CATAPULT_LOCK_POSITION);
    }

    public void releaseCatapult() {
        setCatapultPosition(Constants.CatapultConstants.CATAPULT_RELEASE_POSITION);

    }

    public void fireCatapultMotor() {
        catapultMotor.set(motorpower);
    }

    public void stopMotor() {
        catapultMotor.set(0);
    }


}
