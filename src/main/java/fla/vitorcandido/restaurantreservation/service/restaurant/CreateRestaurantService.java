package fla.vitorcandido.restaurantreservation.service.restaurant;

import fla.vitorcandido.restaurantreservation.dto.restaurant.CreateRestaurantDto;
import fla.vitorcandido.restaurantreservation.entity.exception.ConflictException;
import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateRestaurantService {
    private final RestaurantRepository restaurantRepository;

    public CreateRestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void createRestaurant(CreateRestaurantDto createRestaurantDto) {
        if (!createRestaurantDto.slug.isEmpty()) {
            Optional<Restaurant> alreadyExistingSlugRestaurant = this.restaurantRepository.findBySlug(createRestaurantDto.slug);
            if (alreadyExistingSlugRestaurant.isPresent()) throw new ConflictException("Restaurant slug must be unique");
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setName(createRestaurantDto.name);
        restaurant.setSlug(createRestaurantDto.slug);
        restaurant.setTablesCount(createRestaurantDto.tablesCount);
        restaurant.setReservationDuration(createRestaurantDto.reservationDuration);
        restaurant.setOpenTime(createRestaurantDto.openTime);
        restaurant.setCloseTime(createRestaurantDto.closeTime);

        this.restaurantRepository.save(restaurant);
    }
}
