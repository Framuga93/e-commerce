package gb.demochkin.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import gb.demochkin.ecommerce.dto.UserDto;
import gb.demochkin.ecommerce.entities.Roles;
import gb.demochkin.ecommerce.entities.User;
import gb.demochkin.ecommerce.repositories.RoleRepository;
import gb.demochkin.ecommerce.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Roles role = roleRepository.findByName("USER");
        if (role == null)
            role = checkRoleExist();
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public List<UserDto> findAllUser() {
        return userRepository.findAll().stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Roles checkRoleExist(){
        Roles role = new Roles();
        role.setName("USER");
        return roleRepository.save(role);
    }
}