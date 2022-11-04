package org.project.parser;

import org.project.utility.Utility;

public class Main {

    public static void main(String[] args) {

        String fileName = args[0];
        String inputPath = "C:/Projects/MIDI-parser/files/" + fileName;
        String outputPath = "C:/Projects/MIDI-parser/output";

        Utility utility = new Utility();
        Parser parser = new Parser();
        parser.setInputPath(inputPath);
        parser.setOutputPath(outputPath);
        parser.parseJSON();

    }

}
