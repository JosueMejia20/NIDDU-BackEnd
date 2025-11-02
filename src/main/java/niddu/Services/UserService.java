package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Repository.UserRepository;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }
}
