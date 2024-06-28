package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CartDeliveryTest {

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
@Test
    public void inputForm() {
    open("http://localhost:9999");
    $("[data-test-id='city'] input").setValue("Самара");
    String datePlan = generateDate(4, "dd.MM.yyyy");
    $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
    $("[data-test-id='date'] input").setValue(datePlan);
    $("[data-test-id='name'] input").setValue("Жук-Алексей Алексеевич");
    $("[data-test-id='phone'] input").setValue("+79270000000");
    $("[data-test-id='agreement']").click();
    $("button.button").click();
    $(".notification__content")
            .shouldBe(Condition.visible, Duration.ofSeconds(15))
            .shouldHave(Condition.text("Встреча успешно забронирована на " + datePlan));
}
}
