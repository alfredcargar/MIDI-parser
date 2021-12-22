package org.project.parser;

import org.project.SMF.MIDI;

public class Main {

    public static void main(String[] args) {

        String inputPath = "C:/Projects/MIDI/files/fur_elise.mid";
        String outputPath = "C:/Projects/MIDI/output/file.json";

        MIDI midiFile = new MIDI(inputPath);


        System.out.println();

    }

}
