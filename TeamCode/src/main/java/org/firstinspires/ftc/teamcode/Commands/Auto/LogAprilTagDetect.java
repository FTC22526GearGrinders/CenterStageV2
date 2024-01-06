package org.firstinspires.ftc.teamcode.Commands.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.W_Datalogger_v05;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LogAprilTagDetect extends CommandBase {

    W_Datalogger_v05 tsDL;     // edit name to Datalogger, or the name you used
    String datalogFilename = "myDatalog_Tags";   // modify name for each run
    Drive_Subsystem drive;
    private final CommandOpMode opMode;
    ElapsedTime dataTimer;              // timer object
    ElapsedTime logTimer;// timer object
    int logInterval = 100;               // target interval in milliseconds
    boolean logged = false;
    boolean firstLineWritten = false;
    int lpctr;
    boolean writeRunning = false;

    public LogAprilTagDetect(Drive_Subsystem drive, CommandOpMode opMode) {
        this.opMode = opMode;
        this.drive = drive;
    }

    @Override
    public void initialize() {

        opMode.telemetry.addData("Log Started", "");
        opMode.telemetry.update();

        String out = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        datalogFilename = drive.trajName + out + ".csv";
        tsDL = new W_Datalogger_v05(datalogFilename);

        logged = false;
        lpctr = 0;
        // Instantiate datalog timer.
        dataTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        logTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
//Header fields
        writeRunning = true;
        tsDL.addField("Tag Seen " + String.valueOf(ActiveMotionValues.getActTag()));
        tsDL.addField("TagBearing");
        tsDL.addField("TagRange");
        tsDL.addField("TagYaw");
        tsDL.addField("CalcDist");

        tsDL.firstLine();                        // end first line (row)
        dataTimer.reset();
        logTimer.reset();
        writeRunning = false;
    }

    @Override
    public void execute() {
//data written every time

        String a = ActiveMotionValues.getAprilTagSeen() ? "true" : "false";
        if (firstLineWritten && dataTimer.time() > logInterval) {
            writeRunning = true;
            tsDL.addField(a);
            tsDL.addField(ActiveMotionValues.getActiveTagBearing());
            tsDL.addField(ActiveMotionValues.getActiveTagRange());
            tsDL.addField(ActiveMotionValues.getActiveTagYaw());
            tsDL.addField(ActiveMotionValues.getActiveTagDistance());

            tsDL.newLine();
            dataTimer.reset();
            writeRunning = false;

            lpctr += 1;
        }
        firstLineWritten = true;
    }

    @Override
    public void end(boolean interrupted) {
        tsDL.closeDataLogger();
        opMode.telemetry.addData("Log Ended", "");
        opMode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return ActiveMotionValues.getActTag() == 0 || lpctr >= 10;
    }


}

