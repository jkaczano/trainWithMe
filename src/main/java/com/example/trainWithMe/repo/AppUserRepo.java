package com.example.trainWithMe.repo;

import com.example.trainWithMe.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String username);
}
