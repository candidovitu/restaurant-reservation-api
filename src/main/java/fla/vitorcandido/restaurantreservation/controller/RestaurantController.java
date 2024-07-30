package fla.vitorcandido.restaurantreservation.controller;

import fla.vitorcandido.restaurantreservation.dto.restaurant.CreateRestaurantDto;
import fla.vitorcandido.restaurantreservation.dto.restaurant.UpdateRestaurantDto;
import fla.vitorcandido.restaurantreservation.entity.ResponseMessage;
import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import fla.vitorcandido.restaurantreservation.service.restaurant.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurant")
@RestController
@RequestMapping("/restaurant")
@Validated
public class RestaurantController {
    private final CreateRestaurantService createRestaurantService;
    private final UpdateRestaurantService updateRestaurantService;
    private final DeleteRestaurantService deleteRestaurantService;
    private final GetRestaurantService getRestaurantService;
    private final ListRestaurantsService listRestaurantsService;

    public RestaurantController(
            CreateRestaurantService createRestaurantService,
            UpdateRestaurantService updateRestaurantService,
            DeleteRestaurantService deleteRestaurantService,
            GetRestaurantService getRestaurantService,
            ListRestaurantsService listRestaurantsService
    ) {
        this.createRestaurantService = createRestaurantService;
        this.updateRestaurantService = updateRestaurantService;
        this.deleteRestaurantService = deleteRestaurantService;
        this.getRestaurantService = getRestaurantService;
        this.listRestaurantsService = listRestaurantsService;
    }

    @PostMapping
    public ResponseMessage createRestaurant(@Valid @RequestBody CreateRestaurantDto createRestaurantDto) {
        this.createRestaurantService.createRestaurant(createRestaurantDto);
        return new ResponseMessage("Successfully created restaurant");
    }

    @PutMapping
    public ResponseMessage updateRestaurant(@Valid @RequestBody UpdateRestaurantDto updateRestaurantDto) {
        this.updateRestaurantService.updateRestaurant(updateRestaurantDto);
        return new ResponseMessage("Successfully updated restaurant");
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseMessage deleteRestaurant(@PathVariable("restaurantId") String restaurantId) {
        this.deleteRestaurantService.deleteRestaurant(restaurantId);
        return new ResponseMessage("Successfully deleted restaurant");
    }

    @GetMapping("/list")
    public List<Restaurant> listRestaurants() {
        return this.listRestaurantsService.listRestaurants();
    }

    @GetMapping("/{restaurantIdOrSlug}")
    public Restaurant getRestaurant(@PathVariable("restaurantIdOrSlug") String restaurantIdOrSlug) {
        return this.getRestaurantService.getRestaurant(restaurantIdOrSlug);
    }
}
