package se.cecilia.Bookingservice.dto;

import lombok.*;
import se.cecilia.Bookingservice.model.BookingStatus;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime dateTime;
    private int numberOfPeople;
    private BookingStatus status;
}