package com.example.attendance_tracker.request.discipline_request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDisciplineRequest {
    @NotBlank
    @Length(min = 1, max = 45)
    private String disciplineName;
}
