package org.project.parser;

import org.project.SMF.MIDI;
import org.project.SMF.TrackEvent;
import org.project.utility.LogsManager;
import static org.project.utility.Utility.*;

import java.io.*;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

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
        String BPM = "";
        double tempo = 0;


        for (TrackEvent event : midiFile.getTracks().get(0).getMTrk_Events()) {
            if (event.getEvent().getID() == -1) {
                byte[] arr = new byte[event.getEvent().getLength()];
                sliceByteArray(arr, event);
                switch(event.getEvent().getType()) {
                    case 3: // track name
                        if (midiFile.getHeader().getFormat() == 0) {
                            trackName = new String(arr, Charset.defaultCharset());
                        }
                        // otherwise it's the name of the sequence
                        break;
                    case 81: // set tempo
                        tempo = bytesToInt(arr);
                        break;
                }
            }
        }

        if (tempo == 0) tempo = 500000;
        double bpm = 60e6 / tempo;
        DecimalFormat formatter = new DecimalFormat("0.0");
        formatter.setRoundingMode(RoundingMode.DOWN);
        BPM = String.valueOf(bpm);
        BPM = formatter.format(Double.valueOf(BPM)) + " bpm";

        for (TrackEvent event : midiFile.getTracks().get(1).getMTrk_Events()) {

            if (event.getEvent().getID() == -1) {
                byte[] arr = new byte[event.getEvent().getLength()];
                sliceByteArray(arr, event);
                switch(event.getEvent().getType()) {
                    case 2: // copyright
                        copyright = new String(arr, Charset.defaultCharset());
                        break;
                    case 3: // track name todo from first or second track?
                        if (trackName.isEmpty()) {
                            trackName = new String(arr, Charset.defaultCharset());
                        }
                        break;
                    case 4: // instrument name
                        instrument = new String(arr, Charset.defaultCharset());
                        break;
                }
            }
        }

        json.setCopyright(copyright);
        json.setTrackName(trackName);
        json.setTempo(BPM);
        json.setInstrument(instrument);
        json.createOutput(log);
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

    /**
     * slices the fixed part of the FF event, leaving only the content in the array
     * @param arr
     * @param event
     */
    public void sliceByteArray(byte[] arr, TrackEvent event) {
        // todo start index
        for (int i = 0; i < event.getEvent().getLength(); i++) {
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
