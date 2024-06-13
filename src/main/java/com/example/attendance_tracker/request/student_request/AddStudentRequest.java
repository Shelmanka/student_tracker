package com.example.attendance_tracker.request.student_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequest {
    @NotBlank
    @Length(min = 1, max = 45)
    private String lastname;
    @NotBlank
    @Length(min = 1, max = 45)
    private String firstname;
    @Length(min = 1, max = 45)
    private String middleName;
    @NotBlank
    @Length(min = 1, max = 45)
    private String status;
    @NotNull
    private Long groupId;
}
