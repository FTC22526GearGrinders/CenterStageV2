package org.firstinspires.ftc.teamcode.Commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class WaitArmInPosition extends CommandBase {
    private final ArmSubsystem arm;

    int lpctr;

    public WaitArmInPosition(ArmSubsystem arm) {
        this.arm = arm;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
       lpctr=0;
    }

    @Override
    public void execute() {
        lpctr++;

    }


    @Override
    public void end(boolean interrupted) {
        arm.armMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return lpctr > 5 && arm.profController.atGoal();
    }
}
