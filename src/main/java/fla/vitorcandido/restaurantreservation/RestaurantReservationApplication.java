package fla.vitorcandido.restaurantreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fla.vitorcandido.restaurantreservation")
public class RestaurantReservationApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantReservationApplication.class, args);
	}
}
