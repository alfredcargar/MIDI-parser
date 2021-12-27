package org.project.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {


    /**
     * computes the delta time from a track event
     * @param data
     * @return an integer pair where [0] is the value of the delta time and [1]
     *     its length in bytes.
     */
    public static int[] deltaTime(List<Byte> data) {

        List<Byte> delta_time = splitVLV(data);
        String hex = "";
        for (Byte b : delta_time) {
            hex = hex.concat(String.format("%02X", b));
        }
        return new int[]{Integer.parseInt(hex, 16), delta_time.size()};
    }


    /**
     * Separates a Variable Length Value from the rest of the array.<br>
     * All the bytes of VLV have the msb = 1, the last byte has msb = 0.
     * @param data array of bytes
     * @return the VLV as a list of bytes, max size is 4.
     */
    public static List<Byte> splitVLV(List<Byte> data) {

        List<Byte> vlv = new ArrayList<>();

        for (Byte b : data) {
            vlv.add(b);
            if (b >= 0) break;
        }

        return vlv;
    }


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
