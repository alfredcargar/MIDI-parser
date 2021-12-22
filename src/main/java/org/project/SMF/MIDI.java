package org.project.SMF;

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


    public MIDI(String path) {
        tracks = new ArrayList<>();
        this.readFile(path);
    }

    public void readFile(String path) {

        file = new File(path);

        try {
            content = Files.readAllBytes(file.toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        validate();
        List<Byte> headerData = new ArrayList<>();
        List<Byte> trackData = new ArrayList<>();

        for (int i = 0; i < content.length; i++) {
            if (i < 14) headerData.add(content[i]);
            else trackData.add(content[i]);
        }

        header = new MidiHeader(headerData);
        splitTracks(trackData);

        //todo: read the content in both header and tracks, populating the fields

    }

    /**
     * checks if header ID = MThd
     */
    public void validate() {

        for (int i = 0; i < MidiHeader.getID().length; i++) {
            if (content[i] != MidiHeader.getID()[i]) {
                throw new IllegalArgumentException("Not a valid MIDI file.");
            }
        }
    }

    /**
     * splits the track content in bytes to a list of MidiTracks, populating this.tracks
     * @param trackData
     */
    public void splitTracks(List<Byte> trackData) {

        // base case
        if (trackData.isEmpty()) return;

        List<Byte> content = new ArrayList<>();


        // the length of each chunk is defined in the second group of 4 bytes
        String hex = String.format("%02X", trackData.get(4))
                .concat(String.format("%02X", trackData.get(5))
                .concat(String.format("%02X", trackData.get(6))
                .concat(String.format("%02X", trackData.get(7)))));

        int length = Integer.parseInt(hex, 16);

        // read input from 0 to length+8, set it as content of midiTrack

        for (int i = 0; i < length + 8; i++) {
            content.add(trackData.get(i));
        }

        // sets the content and add to list
        MidiTrack midiTrack = new MidiTrack(content);
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
