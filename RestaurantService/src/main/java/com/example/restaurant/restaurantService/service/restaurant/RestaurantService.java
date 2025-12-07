package com.example.restaurant.restaurantService.service.restaurant;

import com.example.restaurant.restaurantService.dto.restaurant.RestaurantRequestDTO;
import com.example.restaurant.restaurantService.dto.restaurant.RestaurantResponseDTO;
import com.example.restaurant.restaurantService.exception.RestaurantNotFoundException;
import com.example.restaurant.restaurantService.mapper.RestaurantMapper;
import com.example.restaurant.restaurantService.model.Restaurant;
import com.example.restaurant.restaurantService.reposiroty.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = restaurantMapper.toEntity(restaurantRequestDTO);
        return restaurantMapper.toResponseDTO(restaurantRepository.save(restaurant));
    }

    public void removeRestaurant(Long restaurant_id) {
        try{
            restaurantRepository.deleteById(restaurant_id);
        }catch(Exception ex) {
            throw new RestaurantNotFoundException("Restaurant not found, unable to delete!");
        }
    }

    public List<RestaurantResponseDTO> getRestaurantsList(String address){
        List<Restaurant> restaurantList =  restaurantRepository.findByRestaurantAddress(address);

        if(restaurantList.isEmpty()) {
            throw new RestaurantNotFoundException("No restaurants found !");
        }

        List<RestaurantResponseDTO> restaurantResponseDTOList = new ArrayList<>();
        for(Restaurant restaurant : restaurantList) {
            restaurantResponseDTOList.add(restaurantMapper.toResponseDTO(restaurant));
        }
        return restaurantResponseDTOList;
    }

    public RestaurantResponseDTO getRestaurant(Long restaurant_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant Not Found !"));
        return restaurantMapper.toResponseDTO(restaurant);
    }
}
