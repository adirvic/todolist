package br.com.adirvic.task.services;

import br.com.adirvic.task.model.User;
import br.com.adirvic.task.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final @NonNull
    UserRepository userRepository;

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public Boolean existsByUsername(String name){
        return userRepository.existsByUsername(name);
    }

    public Boolean existsByEmail(String name){
        return userRepository.existsByEmail(name);
    }
}