package com.example.attendance_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "lesson")
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id", nullable = false, unique = true)
    private Long lessonId;
    @Column(name = "lesson_date", nullable = false)
    private String lessonDate;
    @Column(name = "lesson_number", nullable = false)
    private int lessonNumber;
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "discipline_id")
    private Discipline discipline;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;
}
