package com.hao.gomall_core.delegates.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager(){

    }

    private static class EventManagerHolder{
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return EventManagerHolder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event){
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action){
        final Event event = EVENTS.get(action);
        if (event == null){
            return new UndefinedEvent();
        }
        return event;
    }

}