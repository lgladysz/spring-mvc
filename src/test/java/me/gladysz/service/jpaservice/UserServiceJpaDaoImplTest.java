package me.gladysz.service.jpaservice;

import me.gladysz.model.User;
import me.gladysz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    @DirtiesContext
    public void shouldAddNewUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("mypassword");

        User savedUser = userService.saveOrUpdate(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEncryptedPassword()).isNotNull();

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());
    }
}