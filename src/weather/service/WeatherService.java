package weather.service;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import weather.client.MetClient;
import weather.model.Event;
import weather.model.WeatherResponse;
import weather.repository.EventRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final EventRepo eventRepository;
    private final MetClient metClient;
    private final Cache<String, WeatherResponse> weatherCache;

    public Optional<WeatherResponse>getWeatherResponse(String eventId) {
            Event event = eventRepository.findById(eventId);
            if (event == null || !event.hasLocation() || !event.isInTheNext7Days()) {
                return Optional.empty();
            }

            String cacheKey = event.getCacheKey();
            WeatherResponse cached = weatherCache.getIfPresent(cacheKey);

            if (cached != null && !cached.isExpired()) {
                return Optional.of(cached);
            }

            WeatherResponse fresh = metClient.fetchWeather(event);
            weatherCache.put(cacheKey, fresh);
            return Optional.of(fresh);
    }
}
