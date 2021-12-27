package org.project.SMF;

public class TrackEvent {

    private Integer delta_time; // VLV: in ticks
    private Event event;

    public TrackEvent() {
    }


    public Integer getDelta_time() {
        return delta_time;
    }

    public void setDelta_time(Integer delta_time) {
        this.delta_time = delta_time;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
