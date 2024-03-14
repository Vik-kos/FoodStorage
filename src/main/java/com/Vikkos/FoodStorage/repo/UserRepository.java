package com.Vikkos.FoodStorage.repo;

import com.Vikkos.FoodStorage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
