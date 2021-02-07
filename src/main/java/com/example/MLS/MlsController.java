package com.example.MLS;

import com.example.MLS.entity.User;
import com.example.MLS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MlsController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping("/")
    public String index(){
        return "start";
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

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        model.addAttribute("listUsers", userRepo.findAll());

        return "users";
    }

    @RequestMapping("/Investment")
    public String investment(){
        return "Investment";
    }

    @GetMapping("/AllInvestments")
    public String allInvestments(Model model) {
        model.addAttribute("user", userRepo.findByEmail("jeffmanassa@gmail.com"));

        return "all_investments";
    }

    @RequestMapping("/AddInvestment")
    public String addInvestment(){
        return "add_investment";
    }



}
