package com.example.attendance_tracker.response.lesson_response;

import com.example.attendance_tracker.entity.AttendanceList;
import com.example.attendance_tracker.entity.Lesson;
import com.example.attendance_tracker.response.discipline_response.GetDisciplineResponse;
import com.example.attendance_tracker.response.group_response.GetGroupResponse;
import com.example.attendance_tracker.response.student_response.GetStudentResponse;
import com.example.attendance_tracker.response.teacher_response.GetTeacherResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetLessonResponse {
    private long lessonId;
    private String lessonDate;
    private int lessonNumber;
    private GetTeacherResponse teacher;
    private GetDisciplineResponse discipline;
    private GetGroupResponse group;
    private List<GetStudentResponse> students = new ArrayList<>();

    public GetLessonResponse(long lessonId, String lessonDate, int lessonNumber, GetTeacherResponse teacher, GetDisciplineResponse discipline, GetGroupResponse group, List<GetStudentResponse> students) {
        this.lessonId = lessonId;
        this.lessonDate = lessonDate;
        this.lessonNumber = lessonNumber;
        this.teacher = teacher;
        this.discipline = discipline;
        this.group = group;
        if (students == null){
            this.students = null;
        } else {
            this.students.addAll(students);
        }
    }

    public GetLessonResponse(Lesson lesson, AttendanceList attendanceList) {
        this(
                lesson.getLessonId(),
                lesson.getLessonDate(),
                lesson.getLessonNumber(),
                new GetTeacherResponse(lesson.getTeacher()),
                new GetDisciplineResponse(lesson.getDiscipline()),
                new GetGroupResponse(lesson.getGroup()),
                attendanceList == null ? null : attendanceList.getStudentsOnLesson().stream().map(GetStudentResponse::new).toList()
        );
    }
}
