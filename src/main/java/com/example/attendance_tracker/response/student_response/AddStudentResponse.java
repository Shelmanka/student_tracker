package com.example.attendance_tracker.response.student_response;

import com.example.attendance_tracker.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddStudentResponse {
    private long studentId;

    public AddStudentResponse(Student student){
        this.studentId = student.getStudentId();
    }
}
