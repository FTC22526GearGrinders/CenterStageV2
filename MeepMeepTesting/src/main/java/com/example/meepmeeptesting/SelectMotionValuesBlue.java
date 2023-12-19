package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;


public class SelectMotionValuesBlue {


    public SelectMotionValuesBlue() {

        boolean bbstart = ActiveMotionValues.getBBStart();
        int lcr = ActiveMotionValues.getLcrpos();

        ActiveMotionValues.setAdvancePose(new Pose2d());
        ActiveMotionValues.setClearPose(new Pose2d());
        ActiveMotionValues.setParkPose(new Pose2d());

        ActiveMotionValues.setStopSecs(.1);


        if (lcr < 1 || lcr > 3) lcr = 1;

        int motionSelected = lcr;

        if (!bbstart) motionSelected += 10;


        switch (motionSelected) {

            //******************************************************************************************
            //******************************************************************************************


            case 1://left tape not truss side

                double dropOffsetX = Constants.RobotConstants.activeDropOffsetPose.getX();

                Pose2d dropOffsetPose = new Pose2d(dropOffsetX, 0, 0);

                Pose2d xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setTurnAngle(FieldConstantsBlue.turnToTagRadians);

                ActiveMotionValues.setActTag(1);

                ActiveMotionValues.setTagLineupPose(FieldConstantsBlue.getActiveTagLineupPose(ActiveMotionValues.getActTag())
                        .minus(dropOffsetPose));

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());
                ActiveMotionValues.setPreParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsBlue.centerPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPoseBB);
                }
                if (ActiveMotionValues.getNearPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsBlue.nearPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPoseBB);
                }

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 2://center straight motion to midddle of center tape


                xyOffsetPose = new Pose2d();

                //robot moves in Y

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.centerDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setTurnAngle(FieldConstantsBlue.turnToTagRadians);

                ActiveMotionValues.setActTag(2);

                ActiveMotionValues.setTagLineupPose(FieldConstantsBlue.getActiveTagLineupPose(ActiveMotionValues.getActTag()));

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                ActiveMotionValues.setParkPose(new Pose2d());
                ActiveMotionValues.setPreParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsBlue.centerPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPoseBB);
                }
                if (ActiveMotionValues.getNearPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsBlue.nearPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPoseBB);
                }

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 3://truss side

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XPYP.startPos);//start pose


                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XPYP.advancePose);


                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XPYP.rightDropPose.minus(xyOffsetPose));


                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XPYP.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsBlue.XPYP.clearPose);

                ActiveMotionValues.setTurnAngle(FieldConstantsBlue.turnToTagRadians);

                ActiveMotionValues.setActTag(3);

                ActiveMotionValues.setTagLineupPose(FieldConstantsBlue.getActiveTagLineupPose(ActiveMotionValues.getActTag()));

                ActiveMotionValues.setPreTagPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .minus(FieldConstantsBlue.AprilTagConstants.tagLookAheadPose));

                ActiveMotionValues.setParkPose(new Pose2d());

                ActiveMotionValues.setParkPose(new Pose2d());
                ActiveMotionValues.setPreParkPose(new Pose2d());

                if (ActiveMotionValues.getCenterPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsBlue.centerPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPoseBB);
                }
                if (ActiveMotionValues.getNearPark()) {
                    ActiveMotionValues.setPreParkPose(FieldConstantsBlue.nearPreParkPoseBB);
                    ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPoseBB);
                }

                break;

            //******************************************************************************************
            //******************************************************************************************

            case 11://left tape

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XMYP.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.leftDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.leftRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsBlue.XMYP.clearPose);


                ActiveMotionValues.setActTag(1);

                setOptions();


                break;

            //******************************************************************************************
            //******************************************************************************************

            case 12://center

                xyOffsetPose = new Pose2d();

                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.centerDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.centerRetractPose);


                ActiveMotionValues.setActTag(2);

                setOptions();

                break;

            //******************************************************************************************
            //******************************************************************************************
            case 13://right tape Blue


                //robot moves in Y

                xyOffsetPose = new Pose2d();


                ActiveMotionValues.setStartPose(FieldConstantsBlue.XMYP.startPose);//start pose

                ActiveMotionValues.setAdvancePose(FieldConstantsBlue.XMYP.advancePose);

                ActiveMotionValues.setDropOffPose(FieldConstantsBlue.XMYP.rightDropPose.minus(xyOffsetPose));

                ActiveMotionValues.setRetractPose(FieldConstantsBlue.XMYP.rightRetractPose);

                ActiveMotionValues.setClearPose(FieldConstantsBlue.XMYP.clearPose);


                ActiveMotionValues.setActTag(3);

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


        double stopTimeSecs = 3;


        if (ActiveMotionValues.getCenterPark())

            ActiveMotionValues.setParkPose(FieldConstantsBlue.centerParkPose);
        else
            ActiveMotionValues.setParkPose(FieldConstantsBlue.nearParkPose);


        if (useTruss) {

            ActiveMotionValues.setTrussSDLineUpPose(FieldConstantsBlue.nearTrussLineUpPose);

            ActiveMotionValues.setOptionStopPose(FieldConstantsBlue.nearOptionPose);

            if (!secondPixel)

                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.nearParkPose);


            else {
                ActiveMotionValues.setClearToTurnPose(FieldConstantsBlue.XMYP.clearToTurnPose);
                ActiveMotionValues.setTurnAngle(FieldConstantsBlue.turnToTagRadians);

                ActiveMotionValues.setStopSecs(stopTimeSecs);
                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));

            }
        }

        if (useStageDoor) {

            if (lcr == 11) {

                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }
            if (lcr == 12) {
                ActiveMotionValues.setClearPose(FieldConstantsBlue.XMYP.centerSDClearPixePose);
                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose2));
            }

            if (lcr == 13) {

                ActiveMotionValues.setTrussSDLineUpPose((FieldConstantsBlue.stageDoorLineUpPose13));
            }

            ActiveMotionValues.setOptionStopPose(FieldConstantsBlue.centerOptionPose);

            if (!secondPixel) {
                ActiveMotionValues.setTurnAngle(Math.toRadians(0));
                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.centerParkPose);
            } else {
                ActiveMotionValues.setTurnAngle(Math.toRadians(-90));
                ActiveMotionValues.setStopSecs(stopTimeSecs);
                ActiveMotionValues.setOptionTargetPose(FieldConstantsBlue.getActiveTagPose(ActiveMotionValues.getActTag())
                        .plus(FieldConstantsBlue.AprilTagConstants.tagStrafeOffsetPose));

            }

        }
    }
}
