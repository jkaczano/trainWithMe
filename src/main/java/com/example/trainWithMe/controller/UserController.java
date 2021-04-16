package com.example.trainWithMe.controller;

import com.example.trainWithMe.model.AppUser;
import com.example.trainWithMe.model.Token;
import com.example.trainWithMe.model.TrainingUnit;
import com.example.trainWithMe.repo.AppUserRepo;
import com.example.trainWithMe.repo.TokenRepo;
import com.example.trainWithMe.repo.TrainingUnitRepo;
import com.example.trainWithMe.service.TrainingUnitService;
import com.example.trainWithMe.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@Controller
public class UserController {

    private UserService userService;
    private TokenRepo tokenRepo;
    private AppUserRepo appUserRepo;
    private TrainingUnitRepo trainingUnitRepo;
    private TrainingUnitService trainingUnitService;

    public UserController(UserService userService, TokenRepo tokenRepo,AppUserRepo appUserRepo, TrainingUnitRepo trainingUnitRepo, TrainingUnitService trainingUnitService) {
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.appUserRepo = appUserRepo;
        this.trainingUnitRepo = trainingUnitRepo;
        this.trainingUnitService = trainingUnitService;
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = authorities.toString();
        if(role.contains("ROLE_ADMIN")) role = "Admin";
        if(role.contains("ROLE_USER")) role = "User";
        model.addAttribute("role",role);
        return "hello";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute("user",new AppUser());
        return "sign-up";
    }
    @PostMapping("/register")
    public String register(AppUser appUser){
        userService.addUser(appUser);
        return "sign-up";
    }
    @GetMapping("/token")
    public String token(@RequestParam String value){
        Token token = tokenRepo.findByValue(value);
        AppUser appUser = token.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "hello";
    }
    @GetMapping("/myPlan")
    public String myPlan(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        return "myPlan";
    }
    @GetMapping("/about")
    public String about(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        return "about";
    }
    @GetMapping("/excercises")
    public String excercises(Model model, Principal principal){
        model.addAttribute("units", trainingUnitRepo.findByUserID((long) 2));
        return "excercises";
    }
    @GetMapping("/progress")
    public String progress(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        return "progress";
    }
    @GetMapping("/addUnit")
    public String addUnit(Model model){
        model.addAttribute("unit",new TrainingUnit());
        return "addUnit";
    }

    @PostMapping("/add")
    public String add(TrainingUnit trainingUnit){
        trainingUnitRepo.save(trainingUnit);
        return "addUnit";
    }
}
