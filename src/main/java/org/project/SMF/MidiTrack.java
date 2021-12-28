package org.project.SMF;

import static org.project.utility.Utility.deltaTime;

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
     * splits the content into class fields, from the 9th byte
     */
    public void split() {
        splitEvents(content.subList(8, content.size()));
    }

    /**
     *
     * @return true if tag matches
     */
    public boolean validate() {

        return true;
    }


    public void splitEvents(List<Byte> listOfEvents) {

        //base case
        if (listOfEvents.isEmpty()) return;

        Event event = new Event();
        List<Byte> content = new ArrayList<>();
        int start = 0;

        int[] delta_time = deltaTime(listOfEvents);
        listOfEvents = listOfEvents.subList(delta_time[1], listOfEvents.size());
        event.setID(listOfEvents.get(0));
        event.setLength(listOfEvents);

        if (event.getID() == -1) {
            event.setType(listOfEvents.get(1));
            start = 3;
        }


        for (int i = 0; i < event.getLength(); i++) {
            content.add(listOfEvents.get(i));
        }

        event.setContent(content);

        //create a new TrackEvent for it
        TrackEvent trackEvent = new TrackEvent();
        trackEvent.setDelta_time(delta_time[0]);
        trackEvent.setEvent(event);
        // add to MtrkEvents
        this.MTrk_Events.add(trackEvent);
        // truncate the input and recursive call
        listOfEvents = listOfEvents.subList(content.size(), listOfEvents.size());
        // if event = FF 2F 00, return;
        splitEvents(listOfEvents);



    }


    public List<TrackEvent> getMTrk_Events() {
        return MTrk_Events;
    }

    public void setMTrk_Events(List<TrackEvent> MTrk_Events) {
        this.MTrk_Events = MTrk_Events;
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
