package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;

public class SelectMotionValuesRed extends CommandBase {

    private CommandOpMode opMode;

    public SelectMotionValuesRed(CommandOpMode opMode) {
        this.opMode = opMode;
    }


    @Override
    public void initialize() {
        opMode.telemetry.addData("RED", "");
        opMode.telemetry.update();
    }

    @Override
    public void execute() {

        boolean bbstart = ActiveMotionValues.getBBStart();
        int lcr = ActiveMotionValues.getLcrpos();
        ActiveMotionValues.setAdvancePose(new Pose2d());
        ActiveMotionValues.setRetractPose(new Pose2d());
        ActiveMotionValues.setClearPose(new Pose2d());
        ActiveMotionValues.setClearToTurnPose(new Pose2d());
        ActiveMotionValues.setParkPose(new Pose2d());
        ActiveMotionValues.setPreParkPose(new Pose2d());
        ActiveMotionValues.setPreTagPose(new Pose2d());
        ActiveMotionValues.setTagLineupPose(new Pose2d());
        ActiveMotionValues.setTurnAngle(0);

        ActiveMotionValues.setStopSecs(.1);


        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;

        switch (motionSelected) {

            //******************************************************************************************
            //******************************************************************************************


            case 1://left tape

                Pose2d xyOffsetPose = new Pose2d();


                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setActTag(4);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XPYM.leftClearPose);

                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);



                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsRed.centerPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPoseBB);
                }
                if (ActiveMotionValues.getNearPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsRed.nearPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPoseBB);
                }


                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.centerDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose((FieldConstantsRed.XPYM.retCPose));

                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);

                ActiveMotionValues.setActTag(5);


                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsRed.centerPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPoseBB);
                }
                if (ActiveMotionValues.getNearPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsRed.nearPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPoseBB);
                }

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://right ta

                double dropOffsetX = Constants.RobotConstants.pixelDropPose.getX();

                Pose2d dropOffsetPose = new Pose2d(dropOffsetX, 0, 0);

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.rightDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose((FieldConstantsRed.getActiveTagLineupPose(ActiveMotionValues.getActTag()).minus(dropOffsetPose)));

                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);

                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPose));

                if (ActiveMotionValues.getCenterPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsRed.centerPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPoseBB);
                }
                if (ActiveMotionValues.getNearPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsRed.nearPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPoseBB);
                }

                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XMYM.clearPose);

                ActiveMotionValues.setActTag(4);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center


                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.centerDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setActTag(5);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.rightDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XMYM.clearPose);

                ActiveMotionValues.setActTag(6);

                setOptions();

                break;

        }
    }

    private void setOptions() {

        int lcr = ActiveMotionValues.getLcrpos() + 10;
        boolean useStageDoor = ActiveMotionValues.getUseStageDoor();
        boolean secondPixel = ActiveMotionValues.getSecondPixel();
        ActiveMotionValues.setParkPose(new Pose2d());

        boolean useTruss = !useStageDoor;

        double stop2PixelSecs = 10;

        if (useTruss) {

            ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsRed.nearTrussLineUpPose);
            ActiveMotionValues.setOptionStopPose(FieldConstantsRed.nearOptionPose);

            if (!secondPixel) {
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.centerParkOptionPose);
                ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose);

            } else {
                ActiveMotionValues.setWaitPartnerClearPose(FieldConstantsRed.nearPartnerClearPose);
                ActiveMotionValues.setStopSecs(stop2PixelSecs);
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.nearOptionPose);
                ActiveMotionValues.setClearToTurnPose(FieldConstantsRed.XMYM.clearToTurnPose);
                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);
                ActiveMotionValues.setTargetPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPose));
            }
        }

        if (useStageDoor) {

            if (lcr == 11 || lcr == 13) {
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setClearPose(FieldConstantsRed.XMYM.centerSDClearPixelPose);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsRed.stageDoorLineUpPose2));
            }

            if (!secondPixel) {
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.centerParkOptionPose);
                ActiveMotionValues.setParkPose(FieldConstantsRed.centerParkPose);
            } else {
                ActiveMotionValues.setWaitPartnerClearPose(FieldConstantsRed.centerPartnerClearPose);
                ActiveMotionValues.setStopSecs(stop2PixelSecs);
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.centerOptionPose);
                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);
                ActiveMotionValues.setTargetPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPose));
            }
        }
    }


    @Override
    public void end(boolean interrupted) {
        opMode.telemetry.addData("SelectRedRun", "");
        opMode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}