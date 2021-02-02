package authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthenticationController(UserRepository userRepository, RoleRepository roleRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/login")
    public String loginUser(@RequestBody User user){
        System.out.println("got here");
        User dbUser = new User();
        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(encoder.encode(user.getPassword()));
        System.out.println(dbUser.getUsername()+ " "+ dbUser.getPassword());
        if(userRepository.findByUsernameAndPassword(dbUser.getUsername(),dbUser.getPassword()) != null)
        {
            System.out.println("exists");
            return "true";
        }
        else{
            System.out.println("nope");
            return "false";
        }

    }

}
