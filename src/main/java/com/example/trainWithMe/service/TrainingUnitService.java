package com.example.trainWithMe.service;

import com.example.trainWithMe.model.TrainingUnit;
import com.example.trainWithMe.repo.TrainingUnitRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingUnitService {

    private TrainingUnitRepo trainingUnitRepo;

    public TrainingUnitService(TrainingUnitRepo trainingUnitRepo) {
        this.trainingUnitRepo = trainingUnitRepo;
    }

    public void addTrainingUnit(TrainingUnit trainingUnit){
        trainingUnitRepo.save(trainingUnit);
    }

//    public List<TrainingUnit> findByUser(Long id){
//        List<TrainingUnit> trainingUnits = new ArrayList<>();
//        //trainingUnitRepo.findTrainingUnitByUserID(id);
//        return trainingUnits;
//    }
}
