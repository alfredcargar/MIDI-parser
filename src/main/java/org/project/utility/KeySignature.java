package org.project.utility;

import java.util.HashMap;
import java.util.Map;


public class KeySignature {

    private byte byteKey;
    private boolean isMajor;

    private static final Map<Byte, String[]> keys = new HashMap<Byte, String[]>() {{
        // flats
        put((byte)-7, new String[]{"Cb Major", "Ab minor"});
        put((byte)-6, new String[]{"Gb Major", "Eb minor"});
        put((byte)-5, new String[]{"Db Major", "Bb minor"});
        put((byte)-4, new String[]{"Ab Major", "F minor"});
        put((byte)-3, new String[]{"Eb Major", "C minor"});
        put((byte)-2, new String[]{"Bb Major", "G minor"});
        put((byte)-1, new String[]{"F Major", "D minor"});
        put((byte)0, new String[]{"C Major", "A minor"});
        // sharps
        put((byte)1, new String[]{"G Major", "E minor"});
        put((byte)2, new String[]{"D Major", "B minor"});
        put((byte)3, new String[]{"A Major", "F# minor"});
        put((byte)4, new String[]{"E Major", "C# minor"});
        put((byte)5, new String[]{"B Major", "G# minor"});
        put((byte)6, new String[]{"F# Major", "D# minor"});
        put((byte)7, new String[]{"C# Major", "A# minor"});
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

        if (isMajor) return keys.get(byteKey)[0];

        return keys.get(byteKey)[1];
    }

    public static Map<Byte, String[]> getKeys() {
        return keys;
    }

    public boolean isMajor() {
        return isMajor;
    }
}
