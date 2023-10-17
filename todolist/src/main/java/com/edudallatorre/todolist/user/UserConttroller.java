package com.edudallatorre.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("users")
public class UserConttroller {
    
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModal userModal) {
        var user = this.userRepository.findByUsername(userModal.getUsername());
        
        if (user != null) {
            System.out.println("User ja existe");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }
        var passwordHashed = BCrypt.withDefaults()
        .hashToString(12, userModal.getPassword().toCharArray());

        userModal.setPassword(passwordHashed);

        var userCreated = this.userRepository.save(userModal);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
