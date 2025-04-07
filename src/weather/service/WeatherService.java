package weather.service;

import weather.model.Event;
import weather.model.WeatherResponse;
import weather.repository.EventRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final EventRepo eventRepository;

    public Object getWeather(String eventId) {
        public WeatherResponse getWeatherResponse(String eventId) {
            Event event = eventRepository.findById(eventId);
            if (event == null || !event.hasLocation() || !event.isInTheNext7Days()) {
                return Optional.empty();
            }
            return null;
        }
    }
}
