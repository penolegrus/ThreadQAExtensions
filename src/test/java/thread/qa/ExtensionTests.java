package thread.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import thread.qa.callbacs.RestExtension;
import thread.qa.callbacs.TestSaver;
import thread.qa.models.Booking;
import thread.qa.models.User;
import thread.qa.parameters.RandomUser;
import thread.qa.parameters.RandomUserResolver;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({RandomUserResolver.class, TestSaver.class, RestExtension.class})
public class ExtensionTests {

    @Test
    public void testWithParam(@RandomUser User testUser) {
        System.out.println(testUser.getName());
        System.out.println(testUser.getJob());
    }

    @Test
    public void failedTest() {
        Assertions.assertEquals(1, 2);
    }

    @Test
    public void restAssuredConfigTest() {
        Booking booking = Booking.Builder().build();
        int actualStatusCode = step("Создание бронирования с валидными параметрами", () ->
                given()
                        .contentType(JSON)
                        .body(booking)
                        .post("/booking")
                        .then()
                        .extract().statusCode()
        );
        step("statusCode ответа эквивалентен 200", () ->
                assertEquals(200, actualStatusCode, "createBooking вернул неверный statusCode")
        );
    }
}
