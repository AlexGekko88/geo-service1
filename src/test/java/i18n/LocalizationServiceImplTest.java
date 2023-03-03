package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.EnumSet;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {
    @DisplayName("Тестирование метода String locale(Country country)")
    @Test
    void localeTest() {
        LocalizationServiceImpl example = new LocalizationServiceImpl();
        EnumSet<Country> allCountries = EnumSet.of(RUSSIA, GERMANY, USA, BRAZIL);

        for (Country country : allCountries) {
            String actual = example.locale(country);
            String expected;
            expected = country == Country.RUSSIA ? "Добро пожаловать" : "Welcome";
            Assertions.assertEquals(expected, actual);
        }
    }
}