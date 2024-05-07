package thread.qa.callbacs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
        );

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                    objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, true);
                    return objectMapper;
                })
        );
    }
}
