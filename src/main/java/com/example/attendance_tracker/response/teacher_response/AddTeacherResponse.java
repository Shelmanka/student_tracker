package com.example.attendance_tracker.response.teacher_response;

import com.example.attendance_tracker.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddTeacherResponse {
    private long teacherId;

    public AddTeacherResponse(Teacher teacher){
        this.teacherId = teacher.getTeacherId();
    }
}
