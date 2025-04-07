package weather.repository;

import org.springframework.stereotype.Repository;
import weather.model.Event;

import java.util.HashMap;
import java.util.Map;

@Repository
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
