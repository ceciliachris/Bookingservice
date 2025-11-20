package se.cecilia.Bookingservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import se.cecilia.Bookingservice.model.BookingStatus;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Date and time is required")
    @Future(message = "Booking must be in the future")
    private LocalDateTime dateTime;

    @Min(value = 1, message = "Number of people must be at least 1")
    @Max(value = 20, message = "Number of people cannot exceed 20")
    private int numberOfPeople;

    private BookingStatus status;
}