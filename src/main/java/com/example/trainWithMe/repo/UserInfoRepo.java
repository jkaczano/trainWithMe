package com.example.trainWithMe.repo;

import com.example.trainWithMe.model.TrainingUnit;
import com.example.trainWithMe.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo,Long> {
    List<UserInfo> findByUserID(Long userID);
}
