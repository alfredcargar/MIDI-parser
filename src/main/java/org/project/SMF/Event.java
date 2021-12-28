package org.project.SMF;

import java.util.List;

import static org.project.utility.Utility.splitVLV;

public class Event {

    private Byte ID;
    private Byte type; // only set for FF events
    private Integer length; // VLV, for convenience will be equal to the whole length including the type byte
    private List<Byte> content;



    public Byte getID() {
        return ID;
    }

    public void setID(Byte ID) {
        this.ID = ID;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(List<Byte> eventList) {

        List<Byte> eventLength;
        String hex = "";

        switch(ID) {
            case -16: // F0, F7 = sys event
            case -9:
                // length starts at second byte
                eventLength = splitVLV(eventList.subList(1, eventList.size()));
                for (Byte b : eventLength) {
                    String bToh = String.format("%02X", b);
                    hex = hex.concat(bToh);
                }
                this.length = Integer.parseInt(hex, 16) + 2;
                break;
            case -1: // FF = meta event
                // length starts at third byte
                eventLength = splitVLV(eventList.subList(2, eventList.size()));
                for (Byte b : eventLength) {
                    String bToh = String.format("%02X", b);
                    hex = hex.concat(bToh);
                }
                this.length = Integer.parseInt(hex, 16) + 3;
                break;
            default:
                // it's a MIDI event, the first byte is the status byte and it's followed by 1 or 2 bytes, depending on the msg
                if (eventList.get(0) >= -128 && eventList.get(0) <= -65) {
                    this.length = 3;
                }
                else {
                    this.length = 2;
                }
                break;
        }
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public List<Byte> getContent() {
        return content;
    }

    public void setContent(List<Byte> content) {
        this.content = content;
    }
}
