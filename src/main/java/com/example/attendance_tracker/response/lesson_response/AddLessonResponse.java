package com.example.attendance_tracker.response.lesson_response;

import com.example.attendance_tracker.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddLessonResponse {
    private long lessonId;

    public AddLessonResponse(Lesson lesson){
        this.lessonId = lesson.getLessonId();
    }
}
