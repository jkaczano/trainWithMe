package com.example.trainWithMe.repo;

import com.example.trainWithMe.model.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingUnitRepo extends JpaRepository<TrainingUnit,Long> {

    List<TrainingUnit> findByUserID(Long userID);
}
