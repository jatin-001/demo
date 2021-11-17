package com.example.dao;

import com.example.utils.ApplicationUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ApplicationUserDao {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
