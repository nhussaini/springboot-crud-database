package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {

        //convert UserDto into User JPA
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        //convert User JPA entity to UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        User user = optionalUser.get();
//        return UserMapper.mapToUserDto(user);
        //refactor the above code to use exception handling
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" ,"id", userId));
        return UserMapper.mapToUserDto(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
      User existingUser = userRepository.findById(user.getId()).orElseThrow(() ->  new ResourceNotFoundException("User" ,"id", user.getId()));
      existingUser.setFirstName(user.getFirstName());
      existingUser.setLastName(user.getLastName());
      existingUser.setEmail(user.getEmail());
      User updatedUser = userRepository.save(existingUser);
      return UserMapper.mapToUserDto(updatedUser);

    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User" ,"id", userId));
        userRepository.deleteById(userId);
    }
}
