package repository;

import model.Event;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class EventRepo {
    private final Map<String, Event> events = new HashMap<>();

    public EventRepo() {
        Event event = new Event();
        events.put(event.getId(), event);
    }

    public Event findById(String id) {
        return events.get(id);
    }
}
