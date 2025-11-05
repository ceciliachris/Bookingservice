package se.cecilia.Bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.cecilia.Bookingservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}