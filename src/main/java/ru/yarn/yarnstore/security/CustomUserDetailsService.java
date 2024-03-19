package ru.yarn.yarnstore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.yarn.yarnstore.entities.User;
import ru.yarn.yarnstore.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByLogin(username).orElseThrow(
                ()-> new UsernameNotFoundException("Такого пользователя нет")
        );
        return new org.springframework.security.core.userdetails
                .User(user.getLogin(),user.getPassword(),user.getRoles());
    }
}
