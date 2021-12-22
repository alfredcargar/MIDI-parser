package org.project.parser;

import org.project.SMF.MIDI;
import org.project.utility.LogsManager;

public class Main {

    public static void main(String[] args) {

        LogsManager log = new LogsManager();

        String inputPath = "C:/Projects/MIDI/files/fur_elise.mid";
        String outputPath = "C:/Projects/MIDI/output/file.json";

        MIDI midiFile = new MIDI(inputPath);
        log.info("Finished reading MIDI file.");

        System.out.println();

    }

}
