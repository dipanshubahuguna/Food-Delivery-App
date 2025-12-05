package com.example.restaurant.restaurantService.service;

import com.example.restaurant.restaurantService.dto.*;
import com.example.restaurant.restaurantService.exception.RestaurantNotFoundException;
import com.example.restaurant.restaurantService.model.RestaurantEntity;
import com.example.restaurant.restaurantService.reposiroty.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        RestaurantEntity restaurantEntity= convertToRestaurantEntity(restaurantRequestDTO);
        return convertToRestaurantResponseDTO(restaurantRepository.save(restaurantEntity));
    }

    public void removeRestaurant(Long restaurant_id) {
        try{
            restaurantRepository.deleteById(restaurant_id);
        }catch(Exception ex) {
            throw new RestaurantNotFoundException("Restaurant not found, unable to delete!");
        }
    }

    public List<RestaurantResponseDTO> getRestaurantList(String address){
        List<RestaurantEntity> restaurantEntityList =  restaurantRepository.findByRestaurantAddress(address);

        if(restaurantEntityList.isEmpty()) {
            throw new RestaurantNotFoundException("No restaurants found !");
        }

        List<RestaurantResponseDTO> restaurantResponseDTOList = new ArrayList<>();
        for(RestaurantEntity restaurantEntity : restaurantEntityList) {
            restaurantResponseDTOList.add(convertToRestaurantResponseDTO(restaurantEntity));
        }
        return restaurantResponseDTOList;
    }

    public RestaurantResponseDTO convertToRestaurantResponseDTO(RestaurantEntity restaurantEntity) {
        if(restaurantEntity == null) return null;

        return new RestaurantResponseDTO(restaurantEntity.getRestaurantId(),
                        restaurantEntity.getRestaurantName(), restaurantEntity.getRestaurantAddress());

    }

    public RestaurantEntity convertToRestaurantEntity(RestaurantRequestDTO restaurantRequestDTO) {
        if(restaurantRequestDTO == null) return null;

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setRestaurantName(restaurantRequestDTO.getRestaurantName());
        restaurantEntity.setRestaurantAddress(restaurantRequestDTO.getRestaurantAddress());
        return restaurantEntity;
    }

}
