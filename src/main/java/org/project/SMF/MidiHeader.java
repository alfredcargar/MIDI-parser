package org.project.SMF;

import java.util.List;

/**
 * <header-ID>(4 btyes) = the header has fixed length, the first 4 bytes define the header id "MThd";
 * <length>(4 bytes) = the next 4 bytes define the length of the header (the bytes that follow), divided in the next 3 fields.
 * <format>(2 bytes) =
 *   0: single multi-channel track
 *   1: one or more simultaneous tracks
 *   2: one or more sequentially independent single-track patterns
 * <num-of-tracks>(2 bytes) = the number of tracks that compose the MIDI file
 * <division>(2 bytes) =
 * ticks per quarter note
 * if the most significant bit is 0, then it's pulses per quarter note (PPQ).
 * if it's 1, then it's SMPTE frames per second.
 */
public class MidiHeader {

    private List<Byte> content;
    private static final byte[] ID = {77, 84, 104, 100};
    private Integer length; // always 6
    private Integer format; // 0, 1 or 2
    private Integer numOfTracks;
    private Integer division;
    private boolean isPPQ;

    public MidiHeader(List<Byte> data) {
        this.content = data;
        this.split();
    }

    /**
     * takes the content and populates class fields
     */
    public void split() {

        String hex = String.format("%02X", content.get(4)).concat(String.format("%02X", content.get(5)))
                .concat(String.format("%02X", content.get(6))).concat(String.format("%02X", content.get(7)));
        length = Integer.parseInt(hex, 16);

        hex = String.format("%02X", content.get(8)).concat(String.format("%02X", content.get(9)));
        format = Integer.parseInt(hex, 16);
        hex = String.format("%02X", content.get(10)).concat(String.format("%02X", content.get(11)));
        numOfTracks = Integer.parseInt(hex, 16);
        hex = String.format("%02X", content.get(12)).concat(String.format("%02X", content.get(13)));
        division = Integer.parseInt(hex, 16);

    }


    public static byte[] getID() {
        return ID;
    }

    public void setContent(List<Byte> content) {
        this.content = content;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Integer getNumOfTracks() {
        return numOfTracks;
    }

    public void setNumOfTracks(Integer numOfTracks) {
        this.numOfTracks = numOfTracks;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public void setPPQ(boolean PPQ) {
        isPPQ = PPQ;
    }
}
