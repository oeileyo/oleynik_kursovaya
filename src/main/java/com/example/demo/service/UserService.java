package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.User;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Boolean checkPW(User user){
        var user_ = userRepository.findByLogin(user.getLogin());
        if (user.getPassword() == user_.getPassword()){
            return true;
        } else {
            return false;
        }
    }

}