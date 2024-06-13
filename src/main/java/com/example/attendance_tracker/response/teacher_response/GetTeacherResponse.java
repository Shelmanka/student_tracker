package com.example.attendance_tracker.response.teacher_response;

import com.example.attendance_tracker.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTeacherResponse {
    private long teacherId;
    private String lastname;
    private String firstname;
    private String middleName;

    public GetTeacherResponse(Teacher teacher){
        this(teacher.getTeacherId(), teacher.getLastname(), teacher.getFirstname(), teacher.getMiddleName());
    }
}
