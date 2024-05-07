package thread.qa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder(builderMethodName = "Builder", setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
    @JsonProperty("firstname")
    @Builder.Default
    @Getter
    private String firstname = "Bill";

    @JsonProperty("lastname")
    @Builder.Default
    @Getter
    private String lastname = "Jackson";

    @JsonProperty("totalprice")
    @Builder.Default
    @Getter
    private Integer totalPrice = 1_000;

    @JsonProperty("depositpaid")
    @Builder.Default
    @Getter
    private Boolean depositPaid = true;

    @JsonProperty("additionalneeds")
    @Builder.Default
    @Getter
    private String additionalNeeds = "Breakfast";

    @JsonProperty("bookingdates")
    @Builder.Default
    @Getter
    private BookingDates bookingDates = BookingDates.Builder().build();
}
