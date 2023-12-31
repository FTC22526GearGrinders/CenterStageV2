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

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.leftAdvancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.leftDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XPYM.leftClearPose);

                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);

                ActiveMotionValues.setActTag(4);


                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPoseLeft));

                ActiveMotionValues.setParkPose(new Pose2d());
                ActiveMotionValues.setPreParkPose(new Pose2d());

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
                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.centerDropPose);


                ActiveMotionValues.setRetractPose(FieldConstantsRed.XPYM.centerRetractPose);

                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);


                ActiveMotionValues.setActTag(5);

                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPoseCenter));

                ActiveMotionValues.setParkPose(new Pose2d());
                ActiveMotionValues.setPreParkPose(new Pose2d());

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

                double dropOffsetX = FieldConstantsRed.pixelRightDropX;

                Pose2d dropOffsetPose = new Pose2d(dropOffsetX, 0, 0);

                ActiveMotionValues.setStartPose(FieldConstantsRed.XPYM.startPos);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XPYM.rightAdvancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XPYM.rightDropPose);

                ActiveMotionValues.setActTag(6);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.getActiveTagLineupPose(ActiveMotionValues.getActTag())
                        .plus(dropOffsetPose));

                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);

                ActiveMotionValues.setPreTagPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsRed.AprilTagConstants.tagLookAheadPoseRight));

                ActiveMotionValues.setParkPose(new Pose2d());
                ActiveMotionValues.setPreParkPose(new Pose2d());

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

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.leftAdvancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.leftDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XMYM.leftClearPose);


                ActiveMotionValues.setActTag(4);

                setOptions();


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.centerDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.centerRetractPose);

                ActiveMotionValues.setActTag(5);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Red


                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsRed.XMYM.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsRed.XMYM.rightAdvancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsRed.XMYM.rightDropPose);

                ActiveMotionValues.setRetractPose(FieldConstantsRed.XMYM.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsRed.XMYM.rightClearPose);

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

        double stop2PixelSecs = 2;

        if (useTruss) {


            if (!secondPixel) {
                ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsRed.nearTrussLineUpPoseNoturn);
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.nearOptionPoseNoTurn);
                ActiveMotionValues.setParkPose(FieldConstantsRed.nearParkPose);

            } else {
                ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsRed.nearTrussLineUpPose);
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.nearOptionPose);
                ActiveMotionValues.setWaitPartnerClearPose(FieldConstantsRed.nearPartnerClearPose);
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.nearOptionPose);
                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);
                ActiveMotionValues.setStopSecs(stop2PixelSecs);
                ActiveMotionValues.setTargetPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPoseTruss));
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
                ActiveMotionValues.setOptionStopPose(FieldConstantsRed.centerOptionPose);
                ActiveMotionValues.setTurnAngle(FieldConstantsRed.turnToTagRadians);
                ActiveMotionValues.setStopSecs(stop2PixelSecs);
                ActiveMotionValues.setTargetPose(FieldConstantsRed.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsRed.AprilTagConstants.tagStrafeOffsetPoseSD));
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