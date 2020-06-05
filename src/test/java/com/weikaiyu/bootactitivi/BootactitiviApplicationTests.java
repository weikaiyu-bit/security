package com.weikaiyu.bootactitivi;

import com.weikaiyu.bootactitivi.dao.UserRepository;
import com.weikaiyu.bootactitivi.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class BootactitiviApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);

        user user = userRepository.findByName("admin");
        boolean checkpw = BCrypt.checkpw("123", user.getPassword());
        System.out.println("checkpwcheckpwcheckpwcheckpw:"+checkpw);
    }

}
