package com.getarrays.userservice.service;

import com.getarrays.userservice.domain.Confirmation;
import com.getarrays.userservice.domain.User;
import com.getarrays.userservice.repository.ConfirmationRepository;
import com.getarrays.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final ConfirmationRepository confirmationRepository;



    @Override
    public User saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        //TODO Send email to user with token
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        //confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }
}
