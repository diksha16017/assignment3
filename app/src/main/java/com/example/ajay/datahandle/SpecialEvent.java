package com.example.ajay.datahandle;

/**
 * Created by Diksha on 01-Oct-16.
 */
public class SpecialEvent {

    //following attributes are the entities of table

    private String eventType;
    private String eventOf;
    private String eventOn;

    public SpecialEvent()
    {

    }

    //getters and setters of all attributes
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventOf() {
        return eventOf;
    }

    public void setEventOf(String eventOf) {
        this.eventOf = eventOf;
    }

    public String getEventOn() {
        return eventOn;
    }

    public void setEventOn(String eventOn) {
        this.eventOn = eventOn;
    }



}
