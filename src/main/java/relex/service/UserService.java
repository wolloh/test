package relex.service;

import relex.controller.User.Models.RegisterUserAccountRequest;
import relex.models.User;

public interface UserService {
    User register(RegisterUserAccountRequest request);
}
