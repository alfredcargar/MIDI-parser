package org.project.utility;

import java.util.HashMap;
import java.util.Map;


public class KeySignature {

    private byte byteKey;
    private boolean isMajor;

    private static final Map<Byte, String> minorKeys = new HashMap<Byte, String>() {{
        put((byte)-7, "Ab minor"); // flats
        put((byte)-6, "Eb minor");
        put((byte)-5, "Bb minor");
        put((byte)-4, "F minor");
        put((byte)-3, "C minor");
        put((byte)-2, "G minor");
        put((byte)-1, "D minor");
        put((byte)0, "A minor");
        put((byte)1, "E minor"); // sharps
        put((byte)2, "B minor");
        put((byte)3, "F# minor");
        put((byte)4, "C# minor");
        put((byte)5, "G# minor");
        put((byte)6, "D# minor");
        put((byte)7, "A# minor");
    }};

    private static final Map<Byte, String> majorKeys = new HashMap<Byte, String>() {{
        put((byte)-7, "Cb Major"); // flats
        put((byte)-6, "Gb Major");
        put((byte)-5, "Db Major");
        put((byte)-4, "Ab Major");
        put((byte)-3, "Eb Major");
        put((byte)-2, "Bb Major");
        put((byte)-1, "F Major");
        put((byte)0, "C Major");
        put((byte)1, "G Major"); // sharps
        put((byte)2, "D Major");
        put((byte)3, "A Major");
        put((byte)4, "E Major");
        put((byte)5, "B Major");
        put((byte)6, "F# Major");
        put((byte)7, "C# Major");
    }};

    /**
     *
     *    key: from -7 (7 flats) to 7 (7 sharps)
     *    major: 0 = major, 1 = minor
     */
    public KeySignature(byte key, byte major) {
        this.byteKey = key;
        this.isMajor = major == 0;
    }

    public String getKey() {

        if (isMajor) {
            return majorKeys.get(byteKey);
        }
        else {
            return minorKeys.get(byteKey);
        }
    }


    public static Map<Byte, String> getMinorKeys() {
        return minorKeys;
    }

    public static Map<Byte, String> getMajorKeys() {
        return majorKeys;
    }

    public boolean isMajor() {
        return isMajor;
    }
}
