package weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weather.model.WeatherResponse;
import weather.service.WeatherService;

import java.util.Optional;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{eventId}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String eventId) {
        Optional<WeatherResponse> weather = weatherService.getWeatherResponse(eventId);
        return weather.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
