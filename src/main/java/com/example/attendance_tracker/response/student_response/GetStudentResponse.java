package com.example.attendance_tracker.response.student_response;

import com.example.attendance_tracker.entity.Student;
import com.example.attendance_tracker.response.group_response.GetGroupResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor
public class GetStudentResponse {
    private long studentId;
    private String lastname;
    private String firstname;
    private String middleName;
    private String status;
    private GetGroupResponse group;

    public GetStudentResponse(Student student){
        this(student.getStudentId(), student.getLastname(), student.getFirstname(), student.getMiddleName(), student.getStatus(), new GetGroupResponse(student.getGroup()));
    }
}
