package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    @DisplayName("Тестирование метода Location byIp(String ip)")
    @ParameterizedTest
    @MethodSource("getArguments")
    void byIpTest(String ip, Location expected) {
        GeoServiceImpl example = new GeoServiceImpl();
        Location actual = example.byIp(ip);

        if (expected != null) {
            Assertions.assertEquals(expected.getCountry(), actual.getCountry());
            Assertions.assertEquals(expected.getCity(), actual.getCity());
            Assertions.assertEquals(expected.getStreet(), actual.getStreet());
            Assertions.assertEquals(expected.getBuilding(), actual.getBuilding());
        } else {
            Assertions.assertNull(actual);
        }

    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.1.95.255", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.1.95.255", new Location("New York", Country.USA, null, 0)),
                Arguments.of("961.95.255", null),
                Arguments.of("не номер", null)
        );
    }
}