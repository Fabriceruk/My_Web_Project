package com.example.question6_userprofile_api.controller;

import com.example.question6_userprofile_api.model.UserProfile;
import com.example.question6_userprofile_api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {
        users.add(new UserProfile(1L,"Hertier","hertier@gmail.com","Hertier Tuyishime",27,"Rwanda","Developer",true));
        users.add(new UserProfile(2L,"aline","aline@gmail.com","Aline Mutesi",23,"Burundi","Student",true));
        users.add(new UserProfile(3L,"mike","mike@gmail.com","Mike Ray",22,"Uganda","Designer",false));
    }

    
    @PostMapping
    public ApiResponse<UserProfile> createUser(@RequestBody UserProfile user) {
        users.add(user);
        return new ApiResponse<>(true,"User profile created successfully",user);
    }

    
    @GetMapping
    public ApiResponse<List<UserProfile>> getAllUsers() {
        return new ApiResponse<>(true,"All users fetched",users);
    }

    
    @GetMapping("/{id}")
    public ApiResponse<UserProfile> getUserById(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                return new ApiResponse<>(true,"User found",u);
            }
        }
        return new ApiResponse<>(false,"User not found",null);
    }

    
    @GetMapping("/search")
    public ApiResponse<List<UserProfile>> searchByUsername(@RequestParam String username) {
        List<UserProfile> result = users.stream().filter(u -> u.getUsername().toLowerCase().contains(username.toLowerCase()))
            .toList();

        return new ApiResponse<>(true,"Search results",result);
    }

    
    @GetMapping("/country/{country}")
    public ApiResponse<List<UserProfile>> getByCountry(@PathVariable String country) {
        List<UserProfile> result = users.stream().filter(u -> u.getCountry().equalsIgnoreCase(country))
            .toList();

        return new ApiResponse<>(true,"Users by country",result);
    }

    @GetMapping("/age")
    public ApiResponse<List<UserProfile>> getByAgeRange(@RequestParam int min,@RequestParam int max) {

        List<UserProfile> result = users.stream() .filter(u -> u.getAge() >= min && u.getAge() <= max).toList();

        return new ApiResponse<>(true,"Users by age range",result);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserProfile> updateUser(@PathVariable Long id,@RequestBody UserProfile updated) {

        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setUsername(updated.getUsername());
                u.setEmail(updated.getEmail());
                u.setFullName(updated.getFullName());
                u.setAge(updated.getAge());
                u.setCountry(updated.getCountry());
                u.setBio(updated.getBio());
                u.setActive(updated.isActive());

                return new ApiResponse<>(true,"User updated",u);
            }
        }
        return new ApiResponse<>(false,"User not found",null);
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<UserProfile> changeStatus(@PathVariable Long id,@RequestParam boolean active) {

        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setActive(active);
                return new ApiResponse<>(true,"User status updated",u);
            }
        }
        return new ApiResponse<>(false,"User not found",null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.getUserId().equals(id));

        if (removed)
            return new ApiResponse<>(true,"User deleted",null);

        return new ApiResponse<>(false,"User not found",null);
    }
}
