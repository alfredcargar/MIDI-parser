package org.project.parser;

public class Main {

    public static void main(String[] args) {


        String inputPath = "C:/Projects/MIDI/files/fur_elise.mid";
        String outputPath = "C:/Projects/MIDI/output/file.json";

        Parser parser = new Parser();
        parser.setInputPath(inputPath);
        parser.setOutputPath(outputPath);
        parser.parseJSON();
        System.out.println();


    }

}
