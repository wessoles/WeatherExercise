package weather.controller;

import weather.model.WeatherResponse;

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
