package com.example.attendance_tracker.response;

import com.example.attendance_tracker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private long userId;

    public UserResponse(User user){
        this.userId = user.getUserId();
    }
}
