package se.cecilia.Bookingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.cecilia.Bookingservice.dto.BookingRequest;
import se.cecilia.Bookingservice.dto.BookingResponse;
import se.cecilia.Bookingservice.exception.BookingNotFound;
import se.cecilia.Bookingservice.exception.InvalidBooking;
import se.cecilia.Bookingservice.mapper.BookingMapper;
import se.cecilia.Bookingservice.model.Booking;
import se.cecilia.Bookingservice.model.BookingStatus;
import se.cecilia.Bookingservice.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> findAll() {
        log.info("Fetching all bookings");
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse findById(Long id) {
        log.info("Fetching booking with id: {}", id);
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFound(id));
        return bookingMapper.toResponse(booking);
    }

    @Override
    @Transactional
    public BookingResponse create(BookingRequest request) {
        log.info("Creating new booking for: {}", request.getEmail());

        validateBookingRequest(request);

        Booking booking = bookingMapper.toEntity(request);
        booking.setStatus(BookingStatus.PENDING);

        Booking savedBooking = bookingRepository.save(booking);
        log.info("Booking created with id: {}", savedBooking.getId());

        return bookingMapper.toResponse(savedBooking);
    }

    @Override
    @Transactional
    public BookingResponse update(Long id, BookingRequest request) {
        log.info("Updating booking with id: {}", id);

        validateBookingRequest(request);

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFound(id));

        bookingMapper.updateEntityFromRequest(booking, request);

        Booking updatedBooking = bookingRepository.save(booking);
        log.info("Booking updated with id: {}", id);

        return bookingMapper.toResponse(updatedBooking);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting booking with id: {}", id);

        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFound(id);
        }

        bookingRepository.deleteById(id);
        log.info("Booking deleted with id: {}", id);
    }

    private void validateBookingRequest(BookingRequest request) {
        if (request.getDateTime().isBefore(LocalDateTime.now())) {
            throw new InvalidBooking("Booking date must be in the future");
        }

        if (request.getNumberOfPeople() <= 0) {
            throw new InvalidBooking("Number of people must be greater than 0");
        }

        if (request.getNumberOfPeople() > 20) {
            throw new InvalidBooking("Cannot book for more than 20 people");
        }
    }
}