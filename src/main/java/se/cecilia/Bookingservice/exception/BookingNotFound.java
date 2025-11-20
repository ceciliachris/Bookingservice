package se.cecilia.Bookingservice.exception;

public class BookingNotFound extends RuntimeException {
    public BookingNotFound(Long id) {
        super("Booking with id " + id + " not found");
    }
}