package se.cecilia.Bookingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.cecilia.Bookingservice.model.Booking;
import se.cecilia.Bookingservice.model.BookingStatus;
import se.cecilia.Bookingservice.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking create(Booking booking) {
        booking.setStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    public Booking update(Long id, Booking updated) {
        Booking booking = findById(id);
        booking.setName(updated.getName());
        booking.setEmail(updated.getEmail());
        booking.setDateTime(updated.getDateTime());
        booking.setNumberOfPeople(updated.getNumberOfPeople());
        booking.setStatus(updated.getStatus());
        return bookingRepository.save(booking);
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}