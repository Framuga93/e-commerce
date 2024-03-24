package gb.demochkin.ecommerce.service;

import gb.demochkin.ecommerce.dto.UserDto;
import gb.demochkin.ecommerce.entities.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUser();

}
