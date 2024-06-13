package com.example.attendance_tracker.request.teacher_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class EditTeacherRequest {
    @NotNull
    private long teacherId;
    @NotBlank
    @Length(min = 1, max = 45)
    private String lastname;
    @NotBlank
    @Length(min = 1, max = 45)
    private String firstname;
    @Length(min = 1, max = 45)
    private String middleName;
}
