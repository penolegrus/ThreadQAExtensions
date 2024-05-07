package thread.qa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Builder(builderMethodName = "Builder", setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
public class BookingDates {

    @JsonProperty("checkin")
    @Builder.Default
    @Getter
    private LocalDate checkin = LocalDate.now();

    @JsonProperty("checkout")
    @Builder.Default
    @Getter
    private LocalDate checkout = LocalDate.now();
}
