package com.manojs.springmvc.service;

import com.manojs.springmvc.entity.User;
import com.manojs.springmvc.exception.ResourceNotFoundException;
import com.manojs.springmvc.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User findUserById(Long id){
        return userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not Found: "+id));
    }
    public User addUser(User user){
        return userRepo.save(user);
    }

    @Transactional
    public User updateUser(User user,Long id){
        User userByName = userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found : "+id));

        if(user.getName()!= null && !user.getName().isEmpty()){
            userByName.setName(user.getName());
        }

        if(user.getEmail()!= null && !user.getEmail().isEmpty()){
            userByName.setEmail(user.getEmail());
        }

        if(user.getPassword()!= null && !user.getPassword().isEmpty()){
            userByName.setPassword(user.getPassword());
        }

        return userByName;
    }
}
