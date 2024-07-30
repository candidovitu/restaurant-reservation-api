package fla.vitorcandido.restaurantreservation.repository;

import fla.vitorcandido.restaurantreservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    @Query("SELECT r FROM reservations r WHERE r.restaurant.id = :restaurantId AND DATE(r.startDate) = DATE(:date)")
    List<Reservation> findAllByRestaurantAndDate(@Param("restaurantId") String restaurantId, @Param("date") Date date);
}