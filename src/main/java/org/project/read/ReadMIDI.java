package org.project.read;

import org.project.SMF.*;
import java.io.File;

public class ReadMIDI {

    private String path;
    private File file;
    private byte[] content;
    private MIDI midiFile;

//    public ReadMIDI(String path) {
//
//        this.path = path;
//        this.openFile();
//        this.validate();
//    }


//    public boolean openFile() {

//        file = new File(path);
//        midiFile = new MIDI(path);
////        MidiHeader header = new MidiHeader();
//        List<MidiTrack> tracks = new ArrayList<>();
//
//        try {
//            content = Files.readAllBytes(file.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        // bytes 0 to 3 = MThd
//        byte[] length = {content[4], content[5], content[6], content[7]};
//        byte[] format = {content[8], content[9]};
//        byte[] nTracks = {content[10], content[11]};
//        int[] division = {content[12] & 0xff, content[13] & 0xff}; // using unsigned bytes 0 to 255
//
//        // checks the msb of the division
//        int msb = division[0] >> 7; // 0 or 1
//        if (msb == 0) {
//            header.setPPQ(true);
//            header.setDivision(division[1]);
//        } // else is 1
//        else {
//            header.setPPQ(false);
//        }
//
//        header.setLength((int) length[3]);
//        header.setFormat((int) format[1]);
//        header.setNumOfTracks((int) nTracks[1]);
//        midiFile.setHeader(header);
//
//        // split tracks into a list
//        String hexContent = "";
//        String[] listOfTracks;
//        try {
//            hexContent = convertByteToHex(content);
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
//
//        listOfTracks = hexContent.split(MidiTrack.getTag());
//
//        // the first index will contain the header
//        for (int i = 1; i < listOfTracks.length; i++) {
            // indexes 1 to [n] contain tracks as hex strings
//            MidiTrack track = new MidiTrack();
//            track.split( listOfTracks[i] );
//            tracks.add(track);
//        }
//        midiFile.setTracks(tracks);

//        return true;
//    }

    /**
     * Checks if file is a valid MIDI format.
     * @return true if midi header present, false if they don't match
     */
    private boolean validate() {

        for (int i = 0; i < MidiHeader.getID().length; i++) {
            if (content[i] != MidiHeader.getID()[i]) {
                return false;
            }
        }
        return true;
    }




    public String getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public byte[] getContent() {
        return content;
    }

    public MIDI getMidiFile() {
        return midiFile;
    }

    public void setMidiFile(MIDI midiFile) {
        this.midiFile = midiFile;
    }
}
