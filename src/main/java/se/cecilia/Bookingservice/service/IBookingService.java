package se.cecilia.Bookingservice.service;

import se.cecilia.Bookingservice.dto.BookingRequest;
import se.cecilia.Bookingservice.dto.BookingResponse;

import java.util.List;

public interface IBookingService {
    List<BookingResponse> findAll();
    BookingResponse findById(Long id);
    BookingResponse create(BookingRequest request);
    BookingResponse update(Long id, BookingRequest request);
    void delete(Long id);
}