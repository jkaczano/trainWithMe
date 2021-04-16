package com.example.trainWithMe;

import com.example.trainWithMe.model.AppUser;
import com.example.trainWithMe.repo.AppUserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserCreate {

    public UserCreate(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder){
        AppUser appUser = new AppUser();
        appUser.setUsername("Janusz");
        appUser.setPassword(passwordEncoder.encode("Janusz123") );
        appUser.setRole("ROLE_ADMIN");
        appUser.setEnabled(true);
        appUser.setEmail("");
        appUserRepo.save(appUser);

        AppUser appUserBogdan = new AppUser();
        appUserBogdan.setUsername("Bogdan");
        appUserBogdan.setPassword(passwordEncoder.encode("Bogdan123") );
        appUserBogdan.setRole("ROLE_USER");
        appUserBogdan.setEnabled(true);
        appUserRepo.save(appUserBogdan);
    }
}
