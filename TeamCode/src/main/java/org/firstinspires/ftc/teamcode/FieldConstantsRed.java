package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.teamcode.FieldConstantsRed.AprilTagConstants.tag4LineupPose;
import static org.firstinspires.ftc.teamcode.FieldConstantsRed.AprilTagConstants.tag5LineupPose;
import static org.firstinspires.ftc.teamcode.FieldConstantsRed.AprilTagConstants.tag6LineupPose;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class FieldConstantsRed {

    public static double startAngle = Math.toRadians(-90);


    public static double turnToTagRadians = Math.toRadians(90);

    public static Pose2d nearPreParkPoseBB = new Pose2d(40, -60, 0);
    public static Pose2d centerPreParkPoseBB = new Pose2d(40, -12, 0);
    public static Pose2d nearParkPoseBB = new Pose2d(55, -60, 0);
    public static Pose2d centerParkPoseBB = new Pose2d(55, -12, 0);
    public static Pose2d nearParkPose = new Pose2d(55, -60, startAngle);
    public static Pose2d centerParkPose = new Pose2d(55, -12, startAngle);

    public static Pose2d nearOptionPose = new Pose2d(36, -60, startAngle);

    public static Pose2d centerOptionPose = new Pose2d(36, -12, startAngle);

    public static Pose2d nearPartnerClearPose = new Pose2d(12, -60, startAngle);
    public static Pose2d centerPartnerClearPose = new Pose2d(12, -12, startAngle);


    public static Pose2d centerParkOptionPose = new Pose2d(36, -12, startAngle);

    public static Pose2d stageDoorLineUpPose13 = new Pose2d(-36, -12, startAngle);

    public static Pose2d stageDoorLineUpPose2 = new Pose2d(-48, -12, startAngle);

    public static Pose2d nearTrussLineUpPose = new Pose2d(-36, -60, startAngle);

    public static double addedYdist = 2;

    public static double pixelCenterComp = .5;//compensate for left to right difference due to hole in pixel


    public static final class AprilTagConstants {

        static final double atagAngle = 0;
        static final double audienceAngle = 180;

        public static double turnToTagRadians = Math.toRadians(90);

        public static final Pose2d tagLookAheadPose = new Pose2d(12 + Constants.RobotConstants.length / 2, -1, Math.toRadians(0));
        public static final Pose2d tagStrafeOffsetPose = new Pose2d(-60.25 + 36, 0, 0);

        // tags 4,5 6
        public static final Pose2d atag4 = new Pose2d(60.25, -30, Math.toRadians(atagAngle));
        public static final Pose2d atag5 = new Pose2d(60.25, -36, Math.toRadians(atagAngle));
        public static final Pose2d atag6 = new Pose2d(60.25, -42, Math.toRadians(atagAngle));

        public static final Pose2d tag4LineupPose = new Pose2d(12, -31, startAngle);

        public static final Pose2d tag5LineupPose = new Pose2d(12, -37, startAngle);

        public static final Pose2d tag6LineupPose = new Pose2d(23.5, -43, startAngle);
        public static final Pose2d atag7 = new Pose2d(-70.25, -40.625, Math.toRadians(audienceAngle));
        public static final Pose2d atag8 = new Pose2d(-70.25, -35.125, Math.toRadians(audienceAngle));

    }

    public static Pose2d getActiveTagPose(int num) {
        int sel = num - 4;
        switch (sel) {
            case 0:
                return FieldConstantsRed.AprilTagConstants.atag4;
            case 1:
                return FieldConstantsRed.AprilTagConstants.atag5;
            case 2:
                return FieldConstantsRed.AprilTagConstants.atag6;
            case 3:
                return FieldConstantsRed.AprilTagConstants.atag7;
            case 4:
                return FieldConstantsRed.AprilTagConstants.atag8;
            default:
                return FieldConstantsRed.AprilTagConstants.atag4;
        }
    }

    public static Pose2d getActiveTagLineupPose(int num) {
        int sel = num - 4;
        switch (sel) {
            case 0:
                return tag4LineupPose;
            case 1:
                return tag5LineupPose;
            case 2:
                return tag6LineupPose;
            default:
                return FieldConstantsRed.AprilTagConstants.atag4;
        }
    }

    public static final class XPYM {
        //left and right are from the view of the robot


        public static final Pose2d startPos = new Pose2d(12, -Constants.FieldConstants.length / 2
                + Constants.RobotConstants.length / 2, startAngle);
        public static final Pose2d rightTapeMid = new Pose2d(23.5, -30);
        public static final Pose2d centerTapeMid = new Pose2d(12, -24.5);
        public static final Pose2d leftTapeMid = new Pose2d(0.5, -30);

        public static final Pose2d advancePose = new Pose2d(12,
                rightTapeMid.getY() + addedYdist - Constants.RobotConstants.length / 2, startAngle);


        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX() + pixelCenterComp,
                leftTapeMid.getY() + addedYdist, startAngle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY() + pixelCenterComp, startAngle).plus(Constants.RobotConstants.pixelCenterDropPose);

        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX() + pixelCenterComp,
                rightTapeMid.getY() + addedYdist, startAngle)
                .plus(Constants.RobotConstants.pixelDropPose);

        public static final Pose2d retLPose = new Pose2d(0, -2);
        public static final Pose2d retRPose = new Pose2d(0, -7.25);
        public static final Pose2d retCPose = new Pose2d(0, -8);
        public static final Pose2d leftRetractPose = leftDropPose.plus(retLPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retCPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retRPose);
        public static final Pose2d leftClearPose = leftRetractPose.plus(new Pose2d(15,0));

    }

    public static final class XMYM {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants


        public static final Pose2d startPose = new Pose2d(-36, -(Constants.FieldConstants.length
                - Constants.RobotConstants.length) / 2, startAngle);


        public static final Pose2d rightTapeMid = new Pose2d(-24.5, -30);
        public static final Pose2d centerTapeMid = new Pose2d(-36, -24.5);
        public static final Pose2d leftTapeMid = new Pose2d(-47.5, -30);
        public static final Pose2d advancePose = new Pose2d(-36,
                rightTapeMid.getY() + addedYdist - Constants.RobotConstants.length / 2, startAngle);

        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX() - pixelCenterComp,
                leftTapeMid.getY() + addedYdist, startAngle)
                .plus(Constants.RobotConstants.pixelDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY() + pixelCenterComp - Constants.RobotConstants.length / 2, startAngle);
        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX() + pixelCenterComp,
                rightTapeMid.getY() + addedYdist, startAngle)
                .plus(Constants.RobotConstants.pixelDropPose);

        public static final Pose2d retPose = new Pose2d(0, -2);

        public static final Pose2d leftRetractPose = leftDropPose.plus(retPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retPose);
        public static final Pose2d clearPose = advancePose.plus(retPose);
        public static final Pose2d centerSDClearPixePose = new Pose2d(leftTapeMid.getX(), centerRetractPose.getY(), startAngle);

        public static final Pose2d clearToTurnPose = new Pose2d(36, -50, startAngle);

        public static final Pose2d centerSDClearPixelPose = new Pose2d(leftTapeMid.getX(), centerRetractPose.getY(), startAngle);

    }
}