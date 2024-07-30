package fla.vitorcandido.restaurantreservation.service.reservation;

import fla.vitorcandido.restaurantreservation.dto.reservation.CreateReservationDto;
import fla.vitorcandido.restaurantreservation.entity.Reservation;
import fla.vitorcandido.restaurantreservation.entity.ReservationAvailability;
import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.entity.exception.ConflictException;
import fla.vitorcandido.restaurantreservation.repository.ReservationRepository;
import fla.vitorcandido.restaurantreservation.service.restaurant.GetRestaurantService;
import fla.vitorcandido.restaurantreservation.utils.AddTimeToDateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateReservationService {
    private final ReservationRepository reservationRepository;

    private final GetRestaurantService getRestaurantService;
    private final CheckReservationAvailabilityService checkReservationAvailabilityService;

    public CreateReservationService(
            ReservationRepository reservationRepository,

            GetRestaurantService getRestaurantService,
            CheckReservationAvailabilityService checkReservationAvailabilityService
    ) {
        this.reservationRepository = reservationRepository;

        this.getRestaurantService = getRestaurantService;
        this.checkReservationAvailabilityService = checkReservationAvailabilityService;
    }

    public void createReservation(CreateReservationDto createReservationDto) {
        Restaurant restaurant = getRestaurantService.getRestaurant(createReservationDto.restaurantId);

        Date reservationStartDate = createReservationDto.date;
        Date reservationEndDate = AddTimeToDateUtil.addTimeToDate(reservationStartDate, restaurant.getReservationDuration());

        ReservationAvailability reservationAvailability = this.checkReservationAvailabilityService.checkReservationAvailability(restaurant.getId(), reservationStartDate);

        if (!reservationAvailability.isAvailable())
            throw new ConflictException("No available table for requested date");

        Reservation reservation = new Reservation();

        reservation.setRestaurant(restaurant);
        reservation.setName(createReservationDto.name);
        reservation.setPhone(createReservationDto.phone);

        reservation.setStartDate(reservationStartDate);
        reservation.setEndDate(reservationEndDate);

        this.reservationRepository.save(reservation);
    }
}
