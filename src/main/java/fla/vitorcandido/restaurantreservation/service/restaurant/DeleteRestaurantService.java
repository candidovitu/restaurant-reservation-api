package fla.vitorcandido.restaurantreservation.service.restaurant;

import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteRestaurantService {
    private final RestaurantRepository restaurantRepository;

    private final GetRestaurantService getRestaurantService;

    public DeleteRestaurantService(
            RestaurantRepository restaurantRepository,

            GetRestaurantService getRestaurantService
    ) {
        this.restaurantRepository = restaurantRepository;

        this.getRestaurantService = getRestaurantService;
    }

    public void deleteRestaurant(String restaurantId) {
        Restaurant restaurant = this.getRestaurantService.getRestaurant(restaurantId);
        this.restaurantRepository.delete(restaurant);
    }
}
