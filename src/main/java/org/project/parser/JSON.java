package org.project.parser;

public class JSON {

    private String duration;
    private String tempo;
    private String timeSignature;
    private String keySignature;
    private String[] instruments;
    private String content;


    public void createOutput() {

        String duration = "\"duration\": " + this.duration;
        String tempo = "\"tempo\": " + this.tempo;
        String time = "\"time signature\": \"" + this.timeSignature + "\"";
        String key = "\"key signature\": \"" + this.keySignature + "\"";

        content = "{"
                + "\n\t" + duration + ","
                + "\n\t" + tempo + ","
                + "\n\t" + time + ","
                + "\n\t" + key + ","
                + "\n" +
                "}";
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

    public String[] getInstruments() {
        return instruments;
    }

    public void setInstruments(String[] instruments) {
        this.instruments = instruments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
