package fla.vitorcandido.restaurantreservation.service.restaurant;

import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.entity.exception.NotFoundException;
import fla.vitorcandido.restaurantreservation.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetRestaurantService {
    private final RestaurantRepository restaurantRepository;

    public GetRestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurant(String restaurantIdOrSlug) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findByIdOrSlug(restaurantIdOrSlug);

        if (restaurant.isEmpty())
            throw new NotFoundException("Restaurant not found");

        return restaurant.get();
    }
}
