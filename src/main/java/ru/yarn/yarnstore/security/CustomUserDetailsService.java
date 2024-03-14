package ru.yarn.yarnstore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.yarn.yarnstore.entities.User;
import ru.yarn.yarnstore.repositories.UserRepository;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь" +  username + "не найден"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(),"{noop}123",
                user.getRoles().stream()
                        .map(it -> new SimpleGrantedAuthority(it.getName()))
                        .collect(Collectors.toList()));
    }
}
