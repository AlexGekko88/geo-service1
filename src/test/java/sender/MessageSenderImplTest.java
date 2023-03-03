package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    GeoServiceImpl geoServiceMock;
    LocalizationServiceImpl localServiceMock;
    MessageSenderImpl message;
    Map<String, String> map = new HashMap<>();

    String ipRussia = "172.";
    String ipNotRussia = "96.";

    @DisplayName("Тестируем язык отправляемого сообщения - русский")
    @Test
    void sendRussian() {
        geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp(ipRussia)).thenReturn(new Location(null, Country.RUSSIA, null, 0));

        localServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        message = new MessageSenderImpl(geoServiceMock, localServiceMock);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipRussia);

        String actual = message.send(map);
        Assertions.assertEquals("Добро пожаловать", actual);
    }

    @DisplayName("Тестируем язык отправляемого сообщения - английский")
    @Test
    void sendNotRussian() {
        geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp(ipNotRussia)).thenReturn(new Location(null, Country.USA, null, 0));

        localServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localServiceMock.locale(Country.USA)).thenReturn("Welcome");

        message = new MessageSenderImpl(geoServiceMock, localServiceMock);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipNotRussia);

        String actual = message.send(map);
        Assertions.assertEquals("Welcome", actual);
    }
}