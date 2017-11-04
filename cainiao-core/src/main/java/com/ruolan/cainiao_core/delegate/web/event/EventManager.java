package com.ruolan.cainiao_core.delegate.web.event;

import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by wuyinlei on 2017/11/4.
 *
 * @function
 */

public class EventManager {

    private static final HashMap<String ,Event> EVENTS = new HashMap<>();

    private EventManager(){

    }

    private static class Holder{
        private static final EventManager INSTANCE = new EventManager();

    }

    public static EventManager getInstance(){
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@Nullable String name,@Nullable Event event){
        EVENTS.put(name,event);
        return this;
    }

    public Event createEvent(@Nullable String action){
        final Event event = EVENTS.get(action);
        if (event == null){
            return new TestEvent();
        }
        return event;
    }



}
