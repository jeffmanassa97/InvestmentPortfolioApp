package com.example.MLS.service;

import com.example.MLS.entity.House;
import com.example.MLS.entity.Image;
import com.example.MLS.entity.Mortgage;
import com.example.MLS.entity.User;
import com.example.MLS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AddInvestmentToUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    public User addInvestment(MultipartFile[] files, House house, Mortgage mortgage) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByEmail(username);

        mortgage.setHouse(house);

        for (MultipartFile file: files) {
            Image image = imageStorageService.getImage(file);
            image.setHouse(house);
            house.getImages().add(image);
        }

        house.setMortgage(mortgage);
        house.setUser(user);

        user.getHouses().add(house);

        return user;
    }
}
