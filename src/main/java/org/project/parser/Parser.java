package org.project.parser;

import org.project.SMF.MIDI;
import org.project.utility.LogsManager;

import java.io.File;

public class Parser {

    private LogsManager log;
    private String inputPath;
    private String outputPath;

    public Parser() {
        log = new LogsManager();
    }


    public boolean parseJSON() {

        File file = new File(inputPath);
        MIDI midiFile = new MIDI(file);
        log.info("Reading MIDI file: " + inputPath);
        midiFile.readFile(log);

        if (!midiFile.validate()) {
            log.error("Not a valid MIDI file.");
            return false;
        }
        log.info("MIDI file read successfully.");


        //todo: map midi into json


        JSON json = new JSON();
        json.createOutput();

        return true;
    }


    public LogsManager getLog() {
        return log;
    }

    public void setLog(LogsManager log) {
        this.log = log;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
