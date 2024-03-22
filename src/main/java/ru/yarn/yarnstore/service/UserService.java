package ru.yarn.yarnstore.service;

import ru.yarn.yarnstore.dto.UserDto;
import ru.yarn.yarnstore.entities.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUser();

}
