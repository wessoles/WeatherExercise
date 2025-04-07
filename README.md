# Weather Forecast Service (Spring Boot)

## How to run

1. Clone the repo:
```bash
git clone https://github.com/wessoles/WeatherExercise.git
```

2. Run the app (Java 17+ and Maven required):
```bash
./mvnw spring-boot:run
```

3. Example request:
```http
GET http://localhost:8080/weather/{eventId}
```

## ✅ Test coverage
Run tests with:
```bash
./mvnw test
```

### Example test class (WeatherServiceTest.java)
```java
@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock private EventRepo eventRepository;
    @Mock private MetClient metClient;
    @Mock private Cache<String, WeatherResponse> weatherCache;
    @InjectMocks private WeatherService weatherService;

    @Test
    void shouldReturnWeatherIfCachedAndFresh() {
        Event event = new Event(...);
        WeatherResponse cached = new WeatherResponse(10, 2.5, Instant.now());

        when(eventRepository.findById("event1")).thenReturn(event);
        when(weatherCache.getIfPresent(anyString())).thenReturn(cached);

        Optional<WeatherResponse> result = weatherService.getWeatherResponse("event1");

        assertTrue(result.isPresent());
        assertEquals(10, result.get().temperature());
    }
}
```

Tests to include:
- Unit test for WeatherService (mock EventRepo and MetClient)
- JSON parsing test for MetClient

## 🚀 Suggested improvements
- Add resilience with retry and timeout
- Add metrics
- Preload weather forecasts for upcoming events
- Swagger documentation

## ☁️ Deployment & production considerations
- Set up monitoring and alerting (Prometheus + Grafana)
- Deploy in Docker containers (Dockerfile + CI/CD pipeline)