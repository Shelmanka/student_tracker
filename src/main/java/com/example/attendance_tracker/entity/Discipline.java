package com.example.attendance_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "discipline")
@AllArgsConstructor
@NoArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discipline_id", nullable = false, unique = true)
    private Long disciplineId;
    @Column(name = "discipline_name", nullable = false)
    private String disciplineName;
}
