package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static Pose2d startPose = new Pose2d();

    public static void main(String[] args) {


        boolean redAlliance = true;

        boolean bbstart = true;//                   aaset to false for start on stack side of truss

        int lcr = 3;//                    left tape ==1, center tape = 2, right tape = 3 from robot view

        boolean useStageDoor = true;

        boolean nearPark = false;

        boolean centerPark = false;

        boolean secondPixel = false;

        if (lcr < 0 || lcr > 3) lcr = 2;


        boolean truss = !useStageDoor;

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


        if (bbstart) {

            MeepMeep meepMeep = new MeepMeep(800);

            if (lcr == 2) {

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        //.UNSTABLE_addTemporalMarkerOffset(1, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .turn(ActiveMotionValues.getTurnAngle())

                                        .lineToLinearHeading(ActiveMotionValues.getPreTagPose())

                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);
            }

            if (lcr == 1 && redAlliance || lcr == 3 && !redAlliance) {


                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        //.UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1.)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .lineToLinearHeading(ActiveMotionValues.getClearPose())

                                        .turn(ActiveMotionValues.getTurnAngle())

                                        .lineToLinearHeading(ActiveMotionValues.getPreTagPose())

                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);


            }

            if (lcr == 3 && redAlliance || lcr == 1 && !redAlliance) {

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        //.UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1.)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .turn(ActiveMotionValues.getTurnAngle())

                                        .lineToLinearHeading(ActiveMotionValues.getPreTagPose())

                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }

        }

        if (!bbstart) {

            MeepMeep meepMeep = new MeepMeep(800);


            if (truss && lcr == 2 && !secondPixel) {
                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        //.UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                                        .waitSeconds(.1)

                                        .lineToLinearHeading(ActiveMotionValues.getParkPose())


                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }
            if (truss && lcr == 2 && secondPixel) {

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        //.UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .turn(ActiveMotionValues.getTurnAngle())

                                        .waitSeconds(.1)

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .lineToLinearHeading(ActiveMotionValues.getWaitPartnerClearPose())

                                        .waitSeconds(ActiveMotionValues.getStopSecs())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                                        .lineToLinearHeading(ActiveMotionValues.getTargetPose())
                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }


            if (truss && (lcr == 1 || lcr == 3) && !secondPixel) {
                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        // .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .lineToLinearHeading((ActiveMotionValues.getClearPose()))

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                                        .waitSeconds(.1)

                                        .lineToLinearHeading(ActiveMotionValues.getParkPose())


                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }
            if (truss && (lcr == 1 || lcr == 3) && secondPixel) {

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        // .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .lineToLinearHeading((ActiveMotionValues.getClearPose()))

                                        .turn(ActiveMotionValues.getTurnAngle())

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .lineToLinearHeading(ActiveMotionValues.getWaitPartnerClearPose())

                                        .waitSeconds(ActiveMotionValues.getStopSecs())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                                        .lineToLinearHeading(ActiveMotionValues.getTargetPose())

                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);
            }


            if (useStageDoor && lcr == 2 && !secondPixel) {

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)


                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())


                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        // .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .lineToLinearHeading(ActiveMotionValues.getClearPose())

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                                        .waitSeconds(.1)

                                        .lineToLinearHeading(ActiveMotionValues.getParkPose())
                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }
            if (useStageDoor && lcr == 2 && secondPixel) {

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        //.UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .lineToLinearHeading(ActiveMotionValues.getClearPose())

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .turn(ActiveMotionValues.getTurnAngle())

                                        .waitSeconds(.1)

                                        .lineToLinearHeading(ActiveMotionValues.getWaitPartnerClearPose())

                                        .waitSeconds(ActiveMotionValues.getStopSecs())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                                        .lineToLinearHeading(ActiveMotionValues.getTargetPose())

                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }


            if (useStageDoor && (lcr == 1 || lcr == 3) && !secondPixel) {
                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        // .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .lineToLinearHeading((ActiveMotionValues.getClearPose()))

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                                        .waitSeconds(.1)

                                        .lineToLinearHeading(ActiveMotionValues.getParkPose())


                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }
            if (useStageDoor && (lcr == 1 || lcr == 3) && secondPixel) {

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                        .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                        .followTrajectorySequence(drive ->

                                drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())


                                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                                        // .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                                        .waitSeconds(1)

                                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                                        .lineToLinearHeading(ActiveMotionValues.getClearPose())

                                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                                        .turn(ActiveMotionValues.getTurnAngle())

                                        .lineToLinearHeading(ActiveMotionValues.getWaitPartnerClearPose())

                                        .waitSeconds(ActiveMotionValues.getStopSecs())

                                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())


                                        .lineToLinearHeading(ActiveMotionValues.getTargetPose())

                                        .build());


                myBot.getDrive().setPoseEstimate(startPose);

                ShowField.showIt(meepMeep, myBot);

            }

        }


    }
}







