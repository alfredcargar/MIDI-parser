package org.project.SMF;

import java.util.ArrayList;
import java.util.List;

public class MidiTrack {

    private static final byte[] ID = {77, 84, 114, 107};
    private List<Byte> content;
    private Integer length;
    private List<TrackEvent> MTrk_Events;

    public MidiTrack(List<Byte> data) {
        content = data;
        MTrk_Events = new ArrayList<>();
        this.split();
    }


    /**
     * splits the content into class fields
     */
    public void split() {
        //todo
        String hex = String.format("%02X", content.get(4)).concat(String.format("%02X", content.get(5)))
                .concat(String.format("%02X", content.get(6))).concat(String.format("%02X", content.get(7)));
        length = Integer.parseInt(hex, 16);

        splitEvents(content.subList(8, content.size()));

    }

    /**
     *
     * @return true if tag matches
     */
    public boolean validate() {
        return true;
    }


    public void splitEvents(List<Byte> events) {

        //base case
        if (events.isEmpty()) return;

        int length;
        List<Byte> content = new ArrayList<>();

        //todo
        // meta-events: 0xFF
        if (events.get(0) == 0 && events.get(1) == -1) {
            length = Integer.parseInt(String.format("%02X", events.get(3)), 16);

            for (int i = 0; i < length + 4; i ++) {
                content.add(events.get(i));
            }

            TrackEvent trackEvent = new TrackEvent(content);
            this.MTrk_Events.add(trackEvent);

            events = events.subList(length + 4, events.size());
            splitEvents(events);
        }

    }



    public void setContent(List<Byte> content) {
        this.content = content;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

}
