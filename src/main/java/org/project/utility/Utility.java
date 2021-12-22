package org.project.utility;

import java.io.IOException;

public class Utility {


    /**
     * formats a byte array in hexadecimal
     * @param input
     * @return
     */
    public static String[] byteToHex(byte[] input) {

        int length = input.length;
        String[] output = new String[length];

        for (int i = 0; i < length; i ++) {
            output[i] = String.format("%02X", input[i]);
        }

        return output;
    }
}
