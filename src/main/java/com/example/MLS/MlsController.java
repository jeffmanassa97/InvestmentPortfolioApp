package com.example.MLS;

import com.example.MLS.entity.House;
import com.example.MLS.entity.Image;
import com.example.MLS.entity.Mortgage;
import com.example.MLS.entity.User;
import com.example.MLS.repository.HouseRepository;
import com.example.MLS.repository.ImagesRepository;
import com.example.MLS.repository.MortgageRepository;
import com.example.MLS.repository.UserRepository;
import com.example.MLS.service.AddInvestmentToUserService;
import com.example.MLS.service.ImageStorageService;
import com.example.MLS.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class MlsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    AddInvestmentToUserService addInvestmentToUserService;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private UtilService utilService;

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

    @RequestMapping(value = "/investment", method = {RequestMethod.GET, RequestMethod.POST})
    public String investment(@RequestParam("id") Long id, Model model){

        System.out.println(id);
//        House house = houseRepository.findById(id).get();
        House house = houseRepository.findByHouseId(id);

        if (house == null)
            System.out.println("house is null");

        model.addAttribute("house", house);
        model.addAttribute("mortgage", house.getMortgage());
        model.addAttribute("imageService", imageStorageService);
        model.addAttribute("utilService", utilService);
        return "investment";
    }

    @GetMapping("/AllInvestments")
    public String allInvestments(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByEmail(username);

        List<House> houses = new ArrayList<>();
        houses.addAll(user.getHouses());
        System.out.println("ADDRESS " + houses.get(0).getAddress() + "\n Size: " + houses.size());
//        System.out.println("ADDRESS " + houses.get(1).getAddress());
//        System.out.println("ADDRESS " + houses.get(2).getAddress());

        model.addAttribute("user", user);
        model.addAttribute("houses", houses);
        model.addAttribute("imageService", new ImageStorageService());

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

        User user = addInvestmentToUserService.addInvestment(files, house, mortgage);
        userRepository.save(user);

        return "add_investment_success";
    }

    @RequestMapping("/MortgageCalculator")
    public String mortgageCalculator() {
        return "mortgage_calculator";
    }

    @RequestMapping("/landing")
    public String landingPage() {
        return "landing_page";
    }


}
