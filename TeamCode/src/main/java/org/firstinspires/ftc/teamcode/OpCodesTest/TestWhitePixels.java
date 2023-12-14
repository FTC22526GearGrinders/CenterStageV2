/* Copyright (c) 2023 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.OpCodesTest;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.Auto.DetectWhitePixelsCamOffset;
import org.firstinspires.ftc.teamcode.Commands.Auto.DetectWhitePixelsCamOffset2;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;

/*
 * This OpMode illustrates the basics of AprilTag recognition and pose estimation, using
 * the easy way.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 */
@Config
@TeleOp(name = "Auto: Test White Pixels ", group = "Test")
//@Disabled
public class TestWhitePixels extends CommandOpMode {

    /**
     * The variable to store our instance of the AprilTag processor.
     */


    private Drive_Subsystem drive;

    public static double xCameraOffset;

    public static double yCameraOffset;

    public static double zCameraOffset;

    public static boolean show2;


    public static int closePortal = 0;

    @Override
    public void initialize() {

        drive = new Drive_Subsystem(this);

        // Wait for the DS start button to be touched.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.update();

    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();


        CommandScheduler.getInstance().schedule(new ParallelCommandGroup(
                new DetectWhitePixelsCamOffset(this, drive,true),
                new DetectWhitePixelsCamOffset2(this, drive,true)));

//        CommandScheduler.getInstance().schedule(
//                new DetectWhitePixelsCamOffset2(this, drive,true));


//       CommandScheduler.getInstance().schedule(
//                new DetectWhitePixelsCamOffset(this, drive,true));


        while (!isStopRequested() && opModeIsActive()) {

            run();

         //   drive.setShow2(show2);

        }
        reset();


        // Save more CPU resources when camera is no longer needed.

    }

}
