package se.cecilia.Bookingservice.exception;

public class InvalidBooking extends RuntimeException {
    public InvalidBooking(String message) {
        super(message);
    }
}