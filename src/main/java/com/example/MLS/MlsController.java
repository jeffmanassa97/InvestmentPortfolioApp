package com.example.MLS;

import com.example.MLS.entity.House;
import com.example.MLS.entity.Image;
import com.example.MLS.entity.Mortgage;
import com.example.MLS.entity.User;
import com.example.MLS.repository.HouseRepository;
import com.example.MLS.repository.MortgageRepository;
import com.example.MLS.repository.UserRepository;
import com.example.MLS.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MlsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @RequestMapping(value = "/process_register", method = {RequestMethod.GET, RequestMethod.POST})
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        model.addAttribute("listUsers", userRepository.findAll());

        return "users";
    }

    @RequestMapping("/Investment")
    public String investment(){
        return "Investment";
    }

    @GetMapping("/AllInvestments")
    public String allInvestments(Model model) {
        model.addAttribute("user", userRepository.findByEmail("jeffmanassa@gmail.com"));

        return "all_investments";
    }

    @RequestMapping("/AddInvestment")
    public String addInvestment(Model model){
        model.addAttribute("house", new House());
        model.addAttribute("mortgage", new Mortgage());

        return "add_investment";
    }

    @RequestMapping(value = "/process_investment", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {"multipart/form-data"})
    public String processHouse(@RequestParam("pics") MultipartFile[] files, @ModelAttribute("house") House house, @ModelAttribute("mortgage") Mortgage mortgage) {
        // Mortgage -> House
        house.setMortgage(mortgage);

        // Images -> House
        Set<Image> images = new HashSet<>();
//        if (images == null)
//            images = new HashSet<>();
        for (MultipartFile file: files) {
            images.add(imageStorageService.getImage(file));
        }
        house.setImages(images);

        // House -> User
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByEmail(username);

        Set<House> houses = user.getHouses();
        if (houses == null)
            houses = new HashSet<>();
        houses.add(house);
        user.setHouses(houses);
        userRepository.save(user);

        return "add_investment_success";
    }


}
