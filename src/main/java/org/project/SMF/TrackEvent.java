package org.project.SMF;

import java.util.List;
public class TrackEvent {

    private Integer delta_time; // in ticks
    private Event event;
    private List<Byte> content;

    public TrackEvent(List<Byte> data) {
        content = data;
    }
}
