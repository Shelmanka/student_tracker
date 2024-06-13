package com.example.attendance_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "teacher")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false, unique = true)
    private Long teacherId;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "middle_name")
    private String middleName;
}
