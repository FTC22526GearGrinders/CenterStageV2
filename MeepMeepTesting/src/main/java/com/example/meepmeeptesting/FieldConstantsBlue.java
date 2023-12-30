package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.FieldConstantsBlue.AprilTagConstants.tag1LineupPose;
import static com.example.meepmeeptesting.FieldConstantsBlue.AprilTagConstants.tag2LineupPose;
import static com.example.meepmeeptesting.FieldConstantsBlue.AprilTagConstants.tag3LineupPose;

import com.acmerobotics.roadrunner.geometry.Pose2d;


public final class FieldConstantsBlue {

    /*
     *
     *
     * */

    public static final double startAngle = Math.toRadians(90);

    public static Pose2d stageDoorLineUpPose13 = new Pose2d(-36, 12, startAngle);

    public static Pose2d stageDoorLineUpPose2 = new Pose2d(-48, 12, startAngle);

    public static Pose2d nearParkOptionPose = new Pose2d(36, 60, startAngle);
    public static Pose2d centerParkOptionPose = new Pose2d(36, 12, startAngle);


    public static Pose2d nearOptionPose = new Pose2d(48 - Constants.RobotConstants.length / 2, 60, 0);

    public static Pose2d nearOptionPoseNoTurn = new Pose2d(36, 60, startAngle);

    public static Pose2d centerOptionPose = new Pose2d(48 - Constants.RobotConstants.length / 2, 12, 0);

    public static Pose2d nearPartnerClearPose = new Pose2d(-12, 60, 0);

    public static Pose2d centerPartnerClearPose = new Pose2d(-12, 12, 0);


    public static double pixelRightDropX = 2;
    public static double pixelRightDropY = 3;
    public static double pixelLeftDropX = 2;
    public static double pixelLeftDropY = 3;
    public static double rightClearX = 15;

    public static double pixelCenterDropY = 1;

    public static Pose2d pixelLeftDropPose = new Pose2d(pixelLeftDropX, -Constants.RobotConstants.length / 2 + pixelLeftDropY, 0);

    public static Pose2d pixelRightDropPose = new Pose2d(pixelRightDropX, -Constants.RobotConstants.length / 2 + pixelRightDropY, 0);

    public static Pose2d pixelCenterDropPose = new Pose2d(0, -Constants.RobotConstants.length / 2 + pixelCenterDropY, 0);


    public static Pose2d nearParkPose = new Pose2d(55, 60, startAngle);
    public static Pose2d centerParkPose = new Pose2d(55, 12, startAngle);

    public static Pose2d nearPreParkPoseBB = new Pose2d(40, 60, 0);
    public static Pose2d centerPreParkPoseBB = new Pose2d(40, 12, 0);
    public static Pose2d nearParkPoseBB = new Pose2d(55, 60, 0);
    public static Pose2d centerParkPoseBB = new Pose2d(55, 12, 0);


    public static Pose2d nearTrussLineUpPose = new Pose2d(-36, 60, 0);

    public static Pose2d nearTrussLineUpPoseNoTurn = new Pose2d(-36, 60, startAngle);


    public static double turnToTagRadians = Math.toRadians(-90);

    public static final class AprilTagConstants {

        static final double atagAngle = 0;

        public static final Pose2d tagLookAheadPoseLeft = new Pose2d(12 + Constants.RobotConstants.length / 2, 0, Math.toRadians(0));
        public static final Pose2d tagLookAheadPoseCenter = new Pose2d(12 + Constants.RobotConstants.length / 2, 0, Math.toRadians(0));
        public static final Pose2d tagLookAheadPoseRight = new Pose2d(12 + Constants.RobotConstants.length / 2, 0, Math.toRadians(0));

        public static final Pose2d tagStrafeOffsetPoseTruss = new Pose2d(-60.25 + 48 - Constants.RobotConstants.length / 2, 0, 0);

        public static final Pose2d tagStrafeOffsetPoseSD = new Pose2d(-60.25 + 48 - Constants.RobotConstants.length / 2, 0, 0);


        public static final Pose2d atag1 = new Pose2d(60.25, 42, Math.toRadians(atagAngle));
        public static final Pose2d atag2 = new Pose2d(60.25, 36, Math.toRadians(atagAngle));
        public static final Pose2d atag3 = new Pose2d(60.25, 30, Math.toRadians(atagAngle));


        public static final Pose2d tag1LineupPose = new Pose2d(23.5, 42, startAngle);

        public static final Pose2d tag2LineupPose = new Pose2d(12, 36, startAngle);

        public static final Pose2d tag3LineupPose = new Pose2d(12, 30, startAngle);


    }

    public static Pose2d getActiveTagPose(int num) {

        switch (num) {
            case 1:
                return AprilTagConstants.atag1;

            case 2:
                return AprilTagConstants.atag2;

            case 3:
                return AprilTagConstants.atag3;

            default:
                return AprilTagConstants.atag1;

        }
    }

    public static Pose2d getActiveTagLineupPose(int num) {
        int sel = num;
        switch (sel) {
            case 1:
                return tag1LineupPose;
            case 2:
                return tag2LineupPose;
            case 3:
                return tag3LineupPose;
            default:
                return tag1LineupPose;
        }
    }

    public static final class XPYP {
        //left and right are from the view of the robot

        public static final Pose2d startPos = new Pose2d(12, 60, startAngle);

        public static final Pose2d leftTapeMid = new Pose2d(23.5, 30);
        public static final Pose2d centerTapeMid = new Pose2d(12, 24.5);
        public static final Pose2d rightTapeMid = new Pose2d(0.5, 30);

        public static final Pose2d leftAdvancePose = new Pose2d(12,
                leftTapeMid.getY() + Constants.RobotConstants.length / 2 - pixelLeftDropY, startAngle
        );
        public static final Pose2d rightAdvancePose = new Pose2d(12,
                rightTapeMid.getY() + Constants.RobotConstants.length / 2 - pixelRightDropY, startAngle
        );

        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(),
                leftTapeMid.getY(), startAngle)
                .minus(pixelLeftDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY(), startAngle).minus(pixelCenterDropPose);

        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(),
                rightTapeMid.getY(), startAngle)
                .minus(pixelRightDropPose);

        public static final Pose2d retLPose = new Pose2d(0, 2);
        public static final Pose2d retRPose = new Pose2d(0, 2);
        public static final Pose2d retCPose = new Pose2d(0, 8);
        public static final Pose2d leftRetractPose = leftDropPose.plus(retLPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retCPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retRPose);
        public static final Pose2d rightClearPose = rightRetractPose.plus(new Pose2d(rightClearX, 0));


    }


    public static final class XMYP {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants

        ;

        public static final Pose2d startPose = new Pose2d(-36, Constants.FieldConstants.length / 2
                - Constants.RobotConstants.length / 2, startAngle);


        public static final Pose2d leftTapeMid = new Pose2d(-24.5, 30);
        public static final Pose2d centerTapeMid = new Pose2d(-36, 24.5);
        public static final Pose2d rightTapeMid = new Pose2d(-47.5, 30);

        public static final Pose2d leftAdvancePose = new Pose2d(-36,
                leftTapeMid.getY() + Constants.RobotConstants.length / 2 - pixelLeftDropY, startAngle
        );
        public static final Pose2d rightAdvancePose = new Pose2d(-36,
                rightTapeMid.getY() + Constants.RobotConstants.length / 2 - pixelRightDropY, startAngle
        );

        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(),
                leftTapeMid.getY(), startAngle)
                .minus(pixelLeftDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY() + Constants.RobotConstants.length / 2, startAngle);
        // .minus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(),
                rightTapeMid.getY(), startAngle)
                .minus(pixelRightDropPose);
        public static final Pose2d retPose = new Pose2d(0, 2);
        public static final Pose2d leftRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retPose);
        public static final Pose2d clearPose = leftAdvancePose.plus(retPose);

        public static final Pose2d centerSDClearPixePose = new Pose2d(rightTapeMid.getX(), centerRetractPose.getY(), startAngle);


        public static final Pose2d clearToTurnPose = new Pose2d(36, 55, startAngle);

    }


}