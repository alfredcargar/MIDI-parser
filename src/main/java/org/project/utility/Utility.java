package org.project.utility;

import java.util.ArrayList;
import java.util.List;

public class Utility {


    /**
     * computes the variable length value from a track event.
     * @param data: bytes of the event, VLV must start at index 0.
     * @return integer pair: [0] = variable value [1] = VLV size in bytes
     */
    public static int[] computeVLV(List<Byte> data) {

        List<Byte> variable = splitVLV(data);
        return new int[]{bytesToInt(variable), variable.size()};
    }


    /**
     * Separates a Variable Length Value from the rest of the array.<br>
     * All the bytes of VLV have the msb = 1, the last byte has msb = 0.
     * @param data array of bytes
     * @return the VLV as a list of bytes, max size is 4.
     */
    public static List<Byte> splitVLV(List<Byte> data) {

        List<Byte> vlv = new ArrayList<>();

        // todo stackoverflow ex
        for (Byte b : data) {
            if (vlv.size() == 4) break;
            vlv.add(b);
            if (b >= 0) break;
        }

        return vlv;
    }


    /**
     * formats a byte array in hexadecimal
     * @param input byte array
     * @return array string in hex
     */
    public static String[] byteToHex(byte[] input) {

        int length = input.length;
        String[] output = new String[length];

        for (int i = 0; i < length; i ++) {
            output[i] = String.format("%02X", input[i]);
        }

        return output;
    }


    /**
     * todo better implementation
     * packs a byte array into an integer
     * @param bytes byte array
     * @return the integer value of the byte array
     */
    public static int bytesToInt(byte[] bytes) {

        switch (bytes.length) {
            case 4:
                return ((bytes[0] & 0xFF) << 24) |
                    ((bytes[1] & 0xFF) << 16) |
                    ((bytes[2] & 0xFF) << 8 ) |
                    ((bytes[3] & 0xFF) << 0 );
            case 3:
                return ((bytes[0] & 0xFF) << 16) |
                        ((bytes[1] & 0xFF) << 8 ) |
                        ((bytes[2] & 0xFF) << 0);
            case 2:
                return ((bytes[0] & 0xFF) << 8) |
                        (bytes[1] & 0xFF << 0);
            default:
                return (bytes[0] & 0xFF);
        }

    }

    public static int bytesToInt(List<Byte> bytes) {

        switch (bytes.size()) {
            case 4:
                return ((bytes.get(0) & 0xFF) << 24) |
                        ((bytes.get(1) & 0xFF) << 16) |
                        ((bytes.get(2) & 0xFF) << 8 ) |
                        ((bytes.get(3) & 0xFF) << 0 );
            case 3:
                return ((bytes.get(0) & 0xFF) << 16) |
                        ((bytes.get(1) & 0xFF) << 8 ) |
                        ((bytes.get(2) & 0xFF) << 0);
            case 2:
                return ((bytes.get(0) & 0xFF) << 8) |
                        (bytes.get(1) & 0xFF << 0);
            default:
                return (bytes.get(0) & 0xFF);
        }

    }
}
