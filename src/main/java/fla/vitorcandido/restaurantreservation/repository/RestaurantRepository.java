package fla.vitorcandido.restaurantreservation.repository;

import fla.vitorcandido.restaurantreservation.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    Optional<Restaurant> findBySlug(String slug);

    @Query("SELECT r FROM restaurants r WHERE r.id = :restaurantIdOrSlug OR r.slug = :restaurantIdOrSlug")
    Optional<Restaurant> findByIdOrSlug(@Param("restaurantIdOrSlug") String restaurantIdOrSlug);

    @Query("SELECT r FROM restaurants r WHERE r.id <> :restaurantId AND r.slug = :restaurantSlug")
    Optional<Restaurant> findOtherBySlug(@Param("restaurantId") String restaurantId, @Param("restaurantSlug") String restaurantSlug);
}