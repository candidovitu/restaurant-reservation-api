package fla.vitorcandido.restaurantreservation.service.restaurant;

import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.entity.exception.NotFoundException;
import fla.vitorcandido.restaurantreservation.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListRestaurantsService {
    private final RestaurantRepository restaurantRepository;

    public ListRestaurantsService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> listRestaurants() {
        return this.restaurantRepository.findAll();
    }
}
