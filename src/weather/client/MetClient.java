package weather.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import weather.model.Event;
import weather.model.WeatherResponse;

import java.time.Duration;
import java.time.Instant;

@Component
public class MetClient {
    private final WebClient client = WebClient.builder()
            .baseUrl("https://api.met.no/weatherapi/locationforecast/2.0/compact")
            .defaultHeader(HttpHeaders.USER_AGENT, "Spond Weather Client")
            .build();

    public WeatherResponse fetchWeather(Event event) {
        String url = String.format("?lat=%f&lon=%f", event.getLatitude(), event.getLongitude());

        JsonNode root = client.get().uri(url).retrieve().bodyToMono(JsonNode.class).block();
        JsonNode timeseries = root.at("/properties/timeseries");

        // Find the data point closest to event start time
        JsonNode closest = findClosest(timeseries, event.getStartTime());
        double temp = closest.at("/data/instant/details/air_temperature").asDouble();
        double wind = closest.at("/data/instant/details/wind_speed").asDouble();

        return new WeatherResponse(temp, wind, Instant.now());
    }

    private JsonNode findClosest(JsonNode timeseries, Instant eventTime) {
        // Simplified: linear search, could be optimized
        long minDiff = Long.MAX_VALUE;
        JsonNode closest = null;
        for (JsonNode node : timeseries) {
            Instant time = Instant.parse(node.get("time").asText());
            long diff = Math.abs(Duration.between(time, eventTime).toMinutes());
            if (diff < minDiff) {
                minDiff = diff;
                closest = node;
            }
        }
        return closest;
    }
}