package org.project.parser;

import org.project.utility.LogsManager;

public class JSON {

    private String copyright;
    private String trackName;
    private String duration;
    private String tempo;
    private String timeSignature;
    private String keySignature;
    private String instrument;
    private String content;


    public void createOutput(LogsManager log) {

        String copyright = "\"copyright\": " + "\"" + this.copyright + "\"";
        String trackName = "\"track name\": " + "\"" + this.trackName + "\"";
        String instrument = "\"instrument\": " + "\"" + this.instrument + "\"";
//        String duration = "\"duration\": " + this.duration;
        String tempo = "\"tempo\": " + "\"" + this.tempo + "\"";
        String time = "\"time signature\": \"" + this.timeSignature + "\"";
        String key = "\"key signature\": \"" + this.keySignature + "\"";

        content = "{"
                + "\n\t" + trackName + ","
                + "\n\t" + copyright + ","
                + "\n\t" + instrument + ","
                + "\n\t" + tempo + ","
                + "\n\t" + time + ","
                + "\n\t" + key
                + "\n" +
                "}";
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright.trim();
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTimeSignature() {
        return timeSignature;
    }

    public void setTimeSignature(String timeSignature) {
        this.timeSignature = timeSignature;
    }

    public String getKeySignature() {
        return keySignature;
    }

    public void setKeySignature(String keySignature) {
        this.keySignature = keySignature;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
