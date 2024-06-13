package com.example.attendance_tracker.request.lesson_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLessonRequest {
    @NotBlank
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}")
    private String lessonDate;
    @NotNull
    @Positive
    private int lessonNumber;
    @NotNull
    private long teacherId;
    @NotNull
    private long disciplineId;
    @NotNull
    private long groupId;
    private List<Long> attendanceList;
}
