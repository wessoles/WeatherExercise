package weather.model;

import java.time.Duration;
import java.time.Instant;

public record WeatherResponse(double temperature, double windSpeed, Instant fetchedAt) {
    public boolean isExpired() {
        return Duration.between(fetchedAt, Instant.now()).toHours() >= 2;
    }
}
