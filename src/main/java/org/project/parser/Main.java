package org.project.parser;

import org.project.utility.Utility;

public class Main {

    public static void main(String[] args) {


        String inputPath = "C:/Projects/MIDI/files/bwv848.mid";
        String outputPath = "C:/Projects/MIDI/output";

        Utility utility = new Utility();
        Parser parser = new Parser();
        parser.setInputPath(inputPath);
        parser.setOutputPath(outputPath);
        parser.parseJSON();
        System.out.println();


    }

}
