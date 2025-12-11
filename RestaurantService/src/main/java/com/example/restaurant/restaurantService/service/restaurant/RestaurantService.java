package com.example.restaurant.restaurantService.service.restaurant;

import com.example.restaurant.restaurantService.dto.restaurant.RestaurantRequestDTO;
import com.example.restaurant.restaurantService.dto.restaurant.RestaurantResponseDTO;
import com.example.restaurant.restaurantService.exception.RestaurantNotFoundException;
import com.example.restaurant.restaurantService.mapper.RestaurantMapper;
import com.example.restaurant.restaurantService.model.Restaurant;
import com.example.restaurant.restaurantService.reposiroty.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        log.debug("Adding restaurant {}",restaurantRequestDTO);
        Restaurant restaurant = restaurantMapper.toEntity(restaurantRequestDTO);
        return restaurantMapper.toResponseDTO(restaurantRepository.save(restaurant));
    }

    public void removeRestaurant(Long restaurant_id) {
        log.debug("Removing restaurant with restaurant id {}",restaurant_id);
        try{
            restaurantRepository.deleteById(restaurant_id);
        }catch(Exception ex) {
            log.debug("Restaurant not found with restaurant id {}",restaurant_id);
            throw new RestaurantNotFoundException("Restaurant not found, unable to delete!");
        }
    }

    public List<RestaurantResponseDTO> getRestaurantsList(String address){
        log.debug("Fetching list of restaurants with {} address",address);
        List<Restaurant> restaurantList =  restaurantRepository.findByRestaurantAddress(address);
        if(restaurantList.isEmpty()) {
            log.debug("No restaurants with {} address",address);
            throw new RestaurantNotFoundException("No restaurants found !");
        }

        List<RestaurantResponseDTO> restaurantResponseDTOList = new ArrayList<>();
        for(Restaurant restaurant : restaurantList) {
            restaurantResponseDTOList.add(restaurantMapper.toResponseDTO(restaurant));
        }
        return restaurantResponseDTOList;
    }

    public RestaurantResponseDTO getRestaurant(Long restaurant_id) {
        log.debug("Fetching restaurant detail with restaurant id {}",restaurant_id);
        Restaurant restaurant = restaurantRepository.findById(restaurant_id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant Not Found !"));
        return restaurantMapper.toResponseDTO(restaurant);
    }
}
