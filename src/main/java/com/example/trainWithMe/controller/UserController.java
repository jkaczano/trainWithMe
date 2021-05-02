package com.example.trainWithMe.controller;

import com.example.trainWithMe.model.AppUser;
import com.example.trainWithMe.model.Token;
import com.example.trainWithMe.model.TrainingUnit;
import com.example.trainWithMe.model.UserInfo;
import com.example.trainWithMe.repo.AppUserRepo;
import com.example.trainWithMe.repo.TokenRepo;
import com.example.trainWithMe.repo.TrainingUnitRepo;
import com.example.trainWithMe.repo.UserInfoRepo;
import com.example.trainWithMe.service.TrainingUnitService;
import com.example.trainWithMe.service.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class UserController {

    private UserService userService;
    private TokenRepo tokenRepo;
    private AppUserRepo appUserRepo;
    private TrainingUnitRepo trainingUnitRepo;
    private TrainingUnitService trainingUnitService;
    private UserInfoRepo userInfoRepo;

    public UserController(UserService userService, TokenRepo tokenRepo,AppUserRepo appUserRepo, TrainingUnitRepo trainingUnitRepo, TrainingUnitService trainingUnitService, UserInfoRepo userInfoRepo) {
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.appUserRepo = appUserRepo;
        this.trainingUnitRepo = trainingUnitRepo;
        this.trainingUnitService = trainingUnitService;
        this.userInfoRepo = userInfoRepo;
    }

    public String getRole(){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = authorities.toString();
        if(role.contains("ROLE_ADMIN")) role = "Coach";
        if(role.contains("ROLE_USER")) role = "User";
        return role;
    }
    @GetMapping("/hello")
    public String hello(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        model.addAttribute("role",getRole());
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
    @GetMapping("/login")
    public String login(){
        return "login";
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();
        model.addAttribute("units", trainingUnitRepo.findByUserID(appUser.getId()));
        model.addAttribute("role",getRole());
        return "myPlan";
    }
    @GetMapping("/about")
    public String about(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        model.addAttribute("role",getRole());
        return "about";
    }
    @GetMapping("/myInfo")
    public String myInfo(Model model, Principal principal){
        model.addAttribute("name",principal.getName());
        model.addAttribute("info",new UserInfo());
        model.addAttribute("role",getRole());
        return "myInfo";
    }
    @GetMapping("/progress")
    public String progress(Principal principal, Model model){
        model.addAttribute("name",principal.getName());
        model.addAttribute("role",getRole());
        return "progress";
    }

    @RequestMapping("/progressChart")
    @ResponseBody
    public String progressChart() {
        List<UserInfo> infoList = userInfoRepo.findAll();
        JsonArray jsonDate = new JsonArray();
        JsonArray jsonMass = new JsonArray();
        JsonArray jsonPullUps = new JsonArray();
        JsonObject json = new JsonObject();
        infoList.forEach(info ->{
            jsonDate.add(String.valueOf(info.getDate()));
            jsonMass.add(info.getBodyMass());
            jsonPullUps.add(info.getPullUps());
        });
        json.add("date",jsonDate);
        json.add("mass",jsonMass);
        json.add("pullUps",jsonPullUps);

        return json.toString();
    }

    @GetMapping("/addUnit")
    public String addUnit(Model model){
        model.addAttribute("unit",new TrainingUnit());
        model.addAttribute("role",getRole());
        List<AppUser> usersList = appUserRepo.findAll();
        model.addAttribute("users",usersList);
        return "addUnit";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @PostMapping("/addUnit")
    public String add(@ModelAttribute("unit") @Valid TrainingUnit trainingUnit, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "addUnit";
        }
        List<AppUser> usersList = appUserRepo.findAll();
        model.addAttribute("users",usersList);
        trainingUnitRepo.save(trainingUnit);
        trainingUnitService.unitNotification(appUserRepo.findById(trainingUnit.getUserID()),trainingUnit);
        return "addUnit";
    }

    @PostMapping("/addInfo")
    public String addInfo(UserInfo userInfo, Model model,Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();
        userInfo.setUserID(appUser.getId());
        System.out.println(userInfo.getDate());
        userInfoRepo.save(userInfo);
        model.addAttribute("name",principal.getName());
        model.addAttribute("role",getRole());
        return "myInfo";
    }
}
