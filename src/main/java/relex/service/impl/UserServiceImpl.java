package relex.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import relex.controller.User.Models.RegisterUserAccountRequest;
import relex.models.User;
import relex.repository.UserRepository;
import relex.service.UserService;
import relex.utils.ShaCreator;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

        private final UserRepository repository;
        @Autowired
        private final ShaCreator shaCreator;
        @SneakyThrows
        @Override
        public User register(RegisterUserAccountRequest request) {
                User userExist=repository.checkEmailAndUserName(request.getEmail(),request.getUserName());
                if(userExist!=null) {
                        throw  new Exception("User already exist");
                }
                User user=new User(request.getEmail(),request.getUserName(),shaCreator.createSHA(request.userName));
                repository.save(user);
                return user;
        }
}
