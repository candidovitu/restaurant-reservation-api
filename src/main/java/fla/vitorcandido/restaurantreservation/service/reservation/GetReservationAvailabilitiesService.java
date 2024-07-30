package fla.vitorcandido.restaurantreservation.service.reservation;

import fla.vitorcandido.restaurantreservation.dto.reservation.CheckReservationAvailabilityDto;
import fla.vitorcandido.restaurantreservation.entity.Reservation;
import fla.vitorcandido.restaurantreservation.entity.ReservationAvailability;
import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.entity.exception.BadRequestException;
import fla.vitorcandido.restaurantreservation.repository.ReservationRepository;
import fla.vitorcandido.restaurantreservation.service.restaurant.GetRestaurantService;
import fla.vitorcandido.restaurantreservation.utils.AddTimeToDateUtil;
import fla.vitorcandido.restaurantreservation.utils.CountReservationsOnDateUtil;
import fla.vitorcandido.restaurantreservation.utils.MergeDateWithTimeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GetReservationAvailabilitiesService {
    private final ReservationRepository reservationRepository;

    private final GetRestaurantService getRestaurantService;

    public GetReservationAvailabilitiesService(
            ReservationRepository reservationRepository,

            GetRestaurantService getRestaurantService
    ) {
        this.reservationRepository = reservationRepository;

        this.getRestaurantService = getRestaurantService;
    }

    private List<Date> getPossibleReservationsDates(Date restaurantOpeningDate, Date restaurantClosingDate, String reservationDuration) {
        List<Date> possibleReservationDates = new ArrayList<>();

        possibleReservationDates.add(restaurantOpeningDate);

        Date possibleDate = restaurantOpeningDate;
        while (possibleDate.before(restaurantClosingDate)) {
            possibleDate = AddTimeToDateUtil.addTimeToDate(possibleDate, reservationDuration);
            possibleReservationDates.add(possibleDate);
        }

        return possibleReservationDates;
    }

    private List<ReservationAvailability> getPossibleReservationsDatesAvailabilities(List<Date> possibleReservationsDates, List<Reservation> requestedDateReservations, int restaurantTablesCount) {
        List<ReservationAvailability> possibleReservationsDatesAvailabilities = new ArrayList<>();

        for (Date possibleReservationsDate : possibleReservationsDates) {
            int reservationsOnRequestedDate = CountReservationsOnDateUtil.countReservationsOnDateUtil(possibleReservationsDate, requestedDateReservations);

            boolean hasAvailableTables = !(reservationsOnRequestedDate >= restaurantTablesCount);

            ReservationAvailability reservationAvailability = new ReservationAvailability();

            reservationAvailability.setDate(possibleReservationsDate);
            reservationAvailability.setAvailable(hasAvailableTables);

            possibleReservationsDatesAvailabilities.add(reservationAvailability);
        }

        return possibleReservationsDatesAvailabilities;
    }

    public List<ReservationAvailability> getReservationAvailabilities(CheckReservationAvailabilityDto checkReservationAvailabilityDto) {
        String restaurantId = checkReservationAvailabilityDto.restaurantId;

        Date requestedDate = checkReservationAvailabilityDto.date;

        Restaurant restaurant = this.getRestaurantService.getRestaurant(restaurantId);

        Date restaurantOpeningDate = MergeDateWithTimeUtil.mergeDateWithTime(requestedDate, restaurant.getOpenTime());
        Date restaurantClosingDate = MergeDateWithTimeUtil.mergeDateWithTime(requestedDate, restaurant.getCloseTime());

        String restaurantReservationDuration = restaurant.getReservationDuration();
        int restaurantTablesCount = restaurant.getTablesCount();

        List<Date> possibleReservationsDates = getPossibleReservationsDates(restaurantOpeningDate, restaurantClosingDate, restaurantReservationDuration);

        List<Reservation> requestedDateReservations = this.reservationRepository.findAllByRestaurantAndDate(restaurantId, requestedDate);

        return this.getPossibleReservationsDatesAvailabilities(possibleReservationsDates, requestedDateReservations, restaurantTablesCount);
    }
}
