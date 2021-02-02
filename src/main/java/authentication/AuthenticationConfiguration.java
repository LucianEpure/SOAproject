package authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@ComponentScan
@EntityScan("authentication")
@EnableJpaRepositories("authentication")
@PropertySource("classpath:db-config.properties")
public class AuthenticationConfiguration {


    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AuthenticationConfiguration (UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void initialize() {

        SCryptPasswordEncoder enc = new SCryptPasswordEncoder();
        Role admin = new Role();
        admin.setRoleName("admin");
        roleRepository.save(admin);

        Role user = new Role();
        user.setRoleName("user");
        roleRepository.save(user);

        User user1 = new User();
        user1.setUsername("admin1");
        user1.setPassword(enc.encode("1234"));
        Role role = roleRepository.findByRoleName("admin");
        List<Role> roleList = user1.getRoles();
        roleList.add(role);
        user1.setRoles(roleList);
        userRepository.save(user1);


    }
}
