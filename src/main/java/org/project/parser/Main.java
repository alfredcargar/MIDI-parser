package org.project.parser;

public class Main {

    public static void main(String[] args) {

        String fileName = args[1];
        String inputPath = "C:/Projects/MIDI-parser/files/" + fileName;
        String outputPath = "C:/Projects/MIDI-parser/output";

        Parser parser = new Parser();
        parser.setInputPath(inputPath);
        parser.setOutputPath(outputPath);
        parser.parseJSON();

    }

}
