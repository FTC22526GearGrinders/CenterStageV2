package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static Pose2d startPose = new Pose2d();


    public static void main(String[] args) {


        boolean redAlliance = false;

        boolean bbstart = true;//aaset to false for start on stack side of truss

        int lcr = 3;//left tape ==1, center tape = 2, right tape = 3 from robot view

        if (lcr < 0 || lcr > 3) lcr = 2;

        boolean useStageDoor = false;

        boolean truss = !useStageDoor;

        boolean nearPark = false;

        boolean centerPark = false;

        boolean secondPixel = true;


        ActiveMotionValues.setRedAlliance(redAlliance);

        ActiveMotionValues.setBBStart(bbstart);
        ActiveMotionValues.setLcrpos(lcr);
        ActiveMotionValues.setUseStageDoor(useStageDoor);

        ActiveMotionValues.setSecondPixel(secondPixel);

        ActiveMotionValues.setNearPark(nearPark);

        ActiveMotionValues.setCenterPark(centerPark);

        if (ActiveMotionValues.getRedAlliance()) {
//
            new SelectMotionValuesRed();
//
        } else {
//
            new SelectMotionValuesBlue();

        }

        MeepMeep meepMeep = new MeepMeep(800);

        if (ActiveMotionValues.getBBStart()) {


            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                    .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                    .followTrajectorySequence(drive ->

                            drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                    .waitSeconds(1)

                                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())//LR tapes only

                                    .lineToLinearHeading(ActiveMotionValues.getClearPose()) // truss side tape only

                                    .lineToLinearHeading(ActiveMotionValues.getTagLineupPose())

                                    .turn(ActiveMotionValues.getTurnAngle())

                                    .lineToLinearHeading(ActiveMotionValues.getPreTagPose())

                                    //  .lineToLinearHeading(ActiveMotionValues.getParkPose())//patk only

                                    .build());


            myBot.getDrive().setPoseEstimate(startPose);

            ShowField.showIt(meepMeep, myBot);


        } else {

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                    .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                    .followTrajectorySequence(drive ->

                            drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

//
                                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                    .waitSeconds(1)

                                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())
//
                                    .lineToLinearHeading(ActiveMotionValues.getClearPose())

                                    //.strafeRight(ActiveMotionValues.getStrafeDistance())

//                                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())
//
//                                    .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())
//
//                                    .turn(ActiveMotionValues.getTurnAngle())
//
//                                    .waitSeconds(ActiveMotionValues.getStopSecs())
////
////
//                                    .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())


                                    .build());


            myBot.getDrive().setPoseEstimate(startPose);

            ShowField.showIt(meepMeep, myBot);


        }

    }

}

