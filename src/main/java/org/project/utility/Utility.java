package org.project.utility;

import java.io.IOException;

public class Utility {


    public static String convertByteToHex(byte[] array) throws IOException {

        StringBuilder sb = new StringBuilder();

        for (Byte element : array) {
            sb.append(String.format("%02X ", element));
        }
        return sb.toString();
    }
}
