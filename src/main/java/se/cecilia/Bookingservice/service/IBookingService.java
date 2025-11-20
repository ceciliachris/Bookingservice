package se.cecilia.Bookingservice.service;

import se.cecilia.Bookingservice.model.Booking;

import java.util.List;

public interface IBookingService {
    List<Booking> findAll();
    Booking findById(Long id);
    Booking create(Booking booking);
    Booking update(Long id, Booking updated);
    void delete(Long id);
}