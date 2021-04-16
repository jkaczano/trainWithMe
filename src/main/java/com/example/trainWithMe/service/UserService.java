package com.example.trainWithMe.service;

import com.example.trainWithMe.model.AppUser;
import com.example.trainWithMe.model.Token;
import com.example.trainWithMe.repo.AppUserRepo;
import com.example.trainWithMe.repo.TokenRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    private TokenRepo tokenRepo;
    private MailService mailService;

    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder,TokenRepo tokenRepo,MailService mailService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    public void addUser(AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser appUser) {
        String value = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(value);
        token.setAppUser(appUser);
        tokenRepo.save(token);

        String url = "http://localhost:8080/token?value=" + value;
        try {
            mailService.sendMail(appUser.getEmail(),"Confirm",url,false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
