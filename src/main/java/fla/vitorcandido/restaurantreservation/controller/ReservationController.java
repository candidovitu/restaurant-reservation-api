package fla.vitorcandido.restaurantreservation.controller;

import fla.vitorcandido.restaurantreservation.dto.reservation.CheckReservationAvailabilityDto;
import fla.vitorcandido.restaurantreservation.dto.reservation.CreateReservationDto;
import fla.vitorcandido.restaurantreservation.entity.ReservationAvailability;
import fla.vitorcandido.restaurantreservation.entity.ResponseMessage;
import fla.vitorcandido.restaurantreservation.service.reservation.CheckReservationAvailabilityService;
import fla.vitorcandido.restaurantreservation.service.reservation.CreateReservationService;
import fla.vitorcandido.restaurantreservation.service.reservation.GetReservationAvailabilitiesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Reservation")
@RestController
@RequestMapping("/reservation")
@Validated
public class ReservationController {
    private final CheckReservationAvailabilityService checkReservationAvailabilityService;
    private final GetReservationAvailabilitiesService getReservationAvailabilitiesService;
    private final CreateReservationService createReservationService;

    public ReservationController(
            CheckReservationAvailabilityService checkReservationAvailabilityService,
            GetReservationAvailabilitiesService getReservationAvailabilitiesService,
            CreateReservationService createReservationService
    ) {
        this.checkReservationAvailabilityService = checkReservationAvailabilityService;
        this.getReservationAvailabilitiesService = getReservationAvailabilitiesService;
        this.createReservationService = createReservationService;
    }

    @PostMapping("/availability")
    public ReservationAvailability checkReservationAvailability(@Valid @RequestBody CheckReservationAvailabilityDto checkReservationAvailabilityDto) {
        return this.checkReservationAvailabilityService.checkReservationAvailability(checkReservationAvailabilityDto.restaurantId, checkReservationAvailabilityDto.date);
    }

    @PostMapping("/availabilities")
    public List<ReservationAvailability> getReservationAvailabilities(@Valid @RequestBody CheckReservationAvailabilityDto checkReservationAvailabilityDto) {
        return this.getReservationAvailabilitiesService.getReservationAvailabilities(checkReservationAvailabilityDto);
    }

    @PostMapping
    public ResponseMessage createReservation(@Valid @RequestBody CreateReservationDto createReservationDto) {
        this.createReservationService.createReservation(createReservationDto);
        return new ResponseMessage("Successfully created reservation");
    }
}
