package com.example.attendance_tracker.service.service_realization;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.entity.User;
import com.example.attendance_tracker.repository.IUserRepository;
import com.example.attendance_tracker.request.UserRequest;
import com.example.attendance_tracker.response.UserResponse;
import com.example.attendance_tracker.response.group_response.AddGroupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse addUser(UserRequest request) {
        User user = new User(null, request.getUserName(), request.getPassword(), request.getRole());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var save = userRepository.save(user);
        return new UserResponse(save.getUserId());
    }
}
