package com.example.trainWithMe.service;

import com.example.trainWithMe.model.AppUser;
import com.example.trainWithMe.model.Token;
import com.example.trainWithMe.model.TrainingUnit;
import com.example.trainWithMe.repo.TrainingUnitRepo;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingUnitService {

    private TrainingUnitRepo trainingUnitRepo;
    private MailService mailService;

    public TrainingUnitService(TrainingUnitRepo trainingUnitRepo, MailService mailService) {
        this.trainingUnitRepo = trainingUnitRepo;
        this.mailService = mailService;
    }

    public void addTrainingUnit(TrainingUnit trainingUnit){
        trainingUnitRepo.save(trainingUnit);
    }

    public void unitNotification(Optional<AppUser> appUser, TrainingUnit trainingUnit) {
        AppUser user = appUser.get();
        try {
            mailService.sendMail(user.getEmail(),"New training unit","Your coach added new training unit: " + trainingUnit.getTopic(),false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
