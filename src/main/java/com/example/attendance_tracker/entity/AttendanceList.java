package com.example.attendance_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "attendance_list")
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_list_id", nullable = false, unique = true)
    private Long attendanceListId;
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id")
    private Lesson lesson;
    @ManyToMany
    @JoinTable(name = "students_on_lesson",
            joinColumns = @JoinColumn(name = "students_on_lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentsOnLesson = new ArrayList<>();
}
