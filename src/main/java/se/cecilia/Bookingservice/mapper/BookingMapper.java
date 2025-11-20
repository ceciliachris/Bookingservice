package se.cecilia.Bookingservice.mapper;

import org.springframework.stereotype.Component;
import se.cecilia.Bookingservice.dto.BookingRequest;
import se.cecilia.Bookingservice.dto.BookingResponse;
import se.cecilia.Bookingservice.model.Booking;

@Component
public class BookingMapper {

    public Booking toEntity(BookingRequest request) {
        return Booking.builder()
                .name(request.getName())
                .email(request.getEmail())
                .dateTime(request.getDateTime())
                .numberOfPeople(request.getNumberOfPeople())
                .status(request.getStatus())
                .build();
    }

    public BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .name(booking.getName())
                .email(booking.getEmail())
                .dateTime(booking.getDateTime())
                .numberOfPeople(booking.getNumberOfPeople())
                .status(booking.getStatus())
                .build();
    }

    public void updateEntityFromRequest(Booking booking, BookingRequest request) {
        booking.setName(request.getName());
        booking.setEmail(request.getEmail());
        booking.setDateTime(request.getDateTime());
        booking.setNumberOfPeople(request.getNumberOfPeople());
        if (request.getStatus() != null) {
            booking.setStatus(request.getStatus());
        }
    }
}