package model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Event {

    private Double latitude;
    private Double longitude;
    private Instant startTime;
    private Instant endTime;
    private String id;

    public boolean hasLocation() {
        return latitude != null && longitude != null;
    }

    public boolean isInTheNext7Days() {
        Instant now = Instant.now();
        return !startTime.isBefore(now) && startTime.isBefore(now.plus(7, ChronoUnit.DAYS));
    }

    public String getId() { return id; }
}
