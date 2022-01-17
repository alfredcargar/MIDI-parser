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

    public void setLength(Integer length) {
        this.length = length;
    }

    public Byte getType() {
        return type;
    }

    /**
     * only for FF (-1) events
     * @param type
     */
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
