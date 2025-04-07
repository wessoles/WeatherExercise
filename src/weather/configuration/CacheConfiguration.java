package weather.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import weather.model.WeatherResponse;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {
    @Bean
    public Cache<String, WeatherResponse> weatherCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(2, TimeUnit.HOURS)
                .maximumSize(10_000)
                .build();
    }
}
