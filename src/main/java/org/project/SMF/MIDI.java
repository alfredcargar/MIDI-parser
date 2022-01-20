package org.project.SMF;

import org.project.utility.LogsManager;
import org.project.utility.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Standard MIDI File structure:
 *  <Header>
 *  n <Midi Tracks>
 */
public class MIDI {

    private MidiHeader header;
    private ArrayList<MidiTrack> tracks;
    private byte[] content;
    private File file;


    public MIDI(File file) {
        this.file = file;
    }

    public void readFile(LogsManager log) {

        try {
            content = Files.readAllBytes(file.toPath());
        }
        catch (IOException e) {
            log.error("Failed to open file: " + file.getPath());
            e.printStackTrace();
        }

        List<Byte> headerData = new ArrayList<>();
        List<Byte> trackData = new ArrayList<>();

        for (int i = 0; i < content.length; i++) {
            if (i < 14) headerData.add(content[i]);
            else trackData.add(content[i]);
        }

        header = new MidiHeader(headerData);
        tracks = new ArrayList<>();
        splitTracks(trackData);
    }

    /**
     * checks if header ID = MThd
     */
    public boolean validate() {

        for (int i = 0; i < MidiHeader.getID().length; i++) {
            if (content[i] != MidiHeader.getID()[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * splits the track content in bytes to a list of MidiTracks, populating the tracks list
     * @param trackData
     */
    public void splitTracks(List<Byte> trackData) {

        // base case
        if (trackData.isEmpty()) return;

        List<Byte> content = new ArrayList<>();

        // the length of each chunk is defined in the second group of 4 bytes
        // todo: check
        String hex = String.format("%02X", trackData.get(4))
                .concat(String.format("%02X", trackData.get(5)))
                .concat(String.format("%02X", trackData.get(6)))
                .concat(String.format("%02X", trackData.get(7)));

        int length = Integer.parseInt(hex, 16);

        // read input from 0 to length+8, set it as content of midiTrack

        for (int i = 0; i < length + 8; i++) {
            content.add(trackData.get(i));
        }

        // sets the content and add to list
        MidiTrack midiTrack = new MidiTrack(content);
        midiTrack.setLength(length);
        this.tracks.add(midiTrack);

        // truncates the chunk that was just added and calls itself on the remaining part
        trackData = trackData.subList(length + 8, trackData.size());
        splitTracks(trackData);
    }



    public MidiHeader getHeader() {
        return header;
    }

    public void setHeader(MidiHeader header) {
        this.header = header;
    }

    public List<MidiTrack> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<MidiTrack> tracks) {
        this.tracks = tracks;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
