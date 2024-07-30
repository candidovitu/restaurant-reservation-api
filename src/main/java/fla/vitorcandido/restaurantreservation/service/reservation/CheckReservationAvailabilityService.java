package fla.vitorcandido.restaurantreservation.service.reservation;

import fla.vitorcandido.restaurantreservation.entity.Reservation;
import fla.vitorcandido.restaurantreservation.entity.ReservationAvailability;
import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.entity.exception.BadRequestException;
import fla.vitorcandido.restaurantreservation.repository.ReservationRepository;
import fla.vitorcandido.restaurantreservation.service.restaurant.GetRestaurantService;
import fla.vitorcandido.restaurantreservation.utils.CountReservationsOnDateUtil;
import fla.vitorcandido.restaurantreservation.utils.MergeDateWithTimeUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckReservationAvailabilityService {
    private final ReservationRepository reservationRepository;

    private final GetRestaurantService getRestaurantService;

    public CheckReservationAvailabilityService(
            ReservationRepository reservationRepository,

            GetRestaurantService getRestaurantService
    ) {
        this.reservationRepository = reservationRepository;

        this.getRestaurantService = getRestaurantService;
    }

    public ReservationAvailability checkReservationAvailability(
            String restaurantId,
            Date requestedDate
    ) {
        Date currentDate = new Date();

        Restaurant restaurant = this.getRestaurantService.getRestaurant(restaurantId);

        if (requestedDate.before(currentDate))
            throw new BadRequestException("Requested date are in the past");

        Date restaurantOpeningDate = MergeDateWithTimeUtil.mergeDateWithTime(requestedDate, restaurant.getOpenTime());
        Date restaurantClosingDate = MergeDateWithTimeUtil.mergeDateWithTime(requestedDate, restaurant.getCloseTime());

        if (requestedDate.before(restaurantOpeningDate) || requestedDate.after(restaurantClosingDate))
            throw new BadRequestException("Restaurant are closed on the requested time");

        List<Reservation> requestedDateReservations = this.reservationRepository.findAllByRestaurantAndDate(restaurantId, requestedDate);

        int reservationsOnRequestedDate = CountReservationsOnDateUtil.countReservationsOnDateUtil(requestedDate, requestedDateReservations);

        boolean hasAvailableTables = !(reservationsOnRequestedDate >= restaurant.getTablesCount());

        ReservationAvailability reservationAvailability = new ReservationAvailability();

        reservationAvailability.setDate(requestedDate);
        reservationAvailability.setAvailable(hasAvailableTables);

        return reservationAvailability;
    }
}
