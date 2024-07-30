package fla.vitorcandido.restaurantreservation.service.restaurant;

import fla.vitorcandido.restaurantreservation.dto.restaurant.CreateRestaurantDto;
import fla.vitorcandido.restaurantreservation.dto.restaurant.UpdateRestaurantDto;
import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.entity.exception.ConflictException;
import fla.vitorcandido.restaurantreservation.entity.exception.NotFoundException;
import fla.vitorcandido.restaurantreservation.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRestaurantService {
    private final RestaurantRepository restaurantRepository;

    private final GetRestaurantService getRestaurantService;

    public UpdateRestaurantService(
            RestaurantRepository restaurantRepository,

            GetRestaurantService getRestaurantService
    ) {
        this.restaurantRepository = restaurantRepository;

        this.getRestaurantService = getRestaurantService;
    }

    public void updateRestaurant(UpdateRestaurantDto updateRestaurantDto) {
        Restaurant restaurant = this.getRestaurantService.getRestaurant(updateRestaurantDto.id);

        if (!updateRestaurantDto.slug.isEmpty()) {
            Optional<Restaurant> alreadyExistingSlugRestaurant = this.restaurantRepository.findOtherBySlug(updateRestaurantDto.id, updateRestaurantDto.slug);
            if (alreadyExistingSlugRestaurant.isPresent())
                throw new ConflictException("Restaurant slug must be unique");
        }

        restaurant.setName(updateRestaurantDto.name);
        restaurant.setSlug(updateRestaurantDto.slug);
        restaurant.setTablesCount(updateRestaurantDto.tablesCount);
        restaurant.setReservationDuration(updateRestaurantDto.reservationDuration);
        restaurant.setOpenTime(updateRestaurantDto.openTime);
        restaurant.setCloseTime(updateRestaurantDto.closeTime);

        this.restaurantRepository.save(restaurant);
    }
}
