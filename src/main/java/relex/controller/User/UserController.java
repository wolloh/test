package relex.controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import relex.controller.User.Models.RegisterUserAccountRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import relex.models.User;
import relex.service.UserService;


@RestController
@RequestMapping(path="/user")
public class UserController{
    private final UserService _userService;

    @Autowired
    public UserController(UserService userService) {

       this._userService=userService;
    }


    @RequestMapping(
            path = "/register",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<User>  RegisterUser(@RequestBody RegisterUserAccountRequest request) {
        User user=_userService.register(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}