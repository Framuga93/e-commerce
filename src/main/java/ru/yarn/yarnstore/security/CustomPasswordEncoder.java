package ru.yarn.yarnstore.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder {
//    public String encode(CharSequence rawPassword) {
//        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(8));
//    }

//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
//    }
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
