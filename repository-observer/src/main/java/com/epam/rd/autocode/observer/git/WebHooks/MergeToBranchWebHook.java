package com.epam.rd.autocode.observer.git.WebHooks;

import com.epam.rd.autocode.observer.git.Event;
import com.epam.rd.autocode.observer.git.WebHook;

import java.util.ArrayList;
import java.util.List;

public class MergeToBranchWebHook implements WebHook {

    private final List<Event> caughtEvents;
    private final String branch;

    public MergeToBranchWebHook(String branch) {
        this.branch = branch;
        this.caughtEvents = new ArrayList<>();
    }

    @Override
    public String branch() {
        return branch;
    }

    @Override
    public Event.Type type() {
        return Event.Type.MERGE;
    }

    @Override
    public List<Event> caughtEvents() {
        return caughtEvents;
    }

    @Override
    public void onEvent(Event event) {
        if (branch.equals(event.branch().toString()) &&
                type() == event.type())
            caughtEvents.add(event);
    }
}