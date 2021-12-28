package org.project.parser;

import org.project.SMF.MIDI;
import org.project.SMF.TrackEvent;
import org.project.utility.LogsManager;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

        JSON json = new JSON();
        String trackName = "";
        String copyright = "";
        String instrument = "";

        //todo: map midi into json
        for (TrackEvent event : midiFile.getTracks().get(1).getMTrk_Events()) {
            if (event.getEvent().getID() == -1) {
                byte[] arr = new byte[event.getEvent().getLength()];
                toByteArray(arr, event);

                switch(event.getEvent().getType()) {
                    case 2: // copyright
                        copyright = new String(arr, Charset.defaultCharset());
                        break;
                    case 3: // track name
                        trackName = new String(arr, Charset.defaultCharset());
                        break;
                    case 4: // instrument name
                        instrument = new String(arr, Charset.defaultCharset());
                        break;

                }
            }
        }

        json.setCopyright(copyright);
        json.setTrackName(trackName);
        json.setInstrument(instrument);
        json.createOutput();
        writeOutput(json.getContent());

        return true;
    }

    public void writeOutput(String output) {

        String filename = "output.json";
        File dir = new File(outputPath);
        File actualFile = new File(dir, filename);

        try {
            Writer out = new BufferedWriter(new FileWriter(actualFile));
            out.write(output);
            out.close();
            log.info("Output created successfully: " + outputPath + "/" + filename);
        }
        catch(Exception e) {
            log.error("Error writing the file.");
            e.printStackTrace();
        }


    }

    public void toByteArray(byte[] arr, TrackEvent event) {
        // todo start index
        for (int i = 3; i < event.getEvent().getLength(); i++) {
            arr[i] = event.getEvent().getContent().get(i);
        }
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
