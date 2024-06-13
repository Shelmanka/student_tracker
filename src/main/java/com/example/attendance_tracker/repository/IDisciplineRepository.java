package com.example.attendance_tracker.repository;

import com.example.attendance_tracker.entity.Discipline;
import com.example.attendance_tracker.entity.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisciplineRepository extends JpaRepository<Discipline, Long> {
        @Transactional
        @Modifying
        @Query("UPDATE Discipline AS d " +
                "SET d.disciplineName = :disciplineName " +
                "WHERE d.disciplineId = :disciplineId")
        void update(@Param(value = "disciplineName") String disciplineName,
                    @Param(value = "disciplineId") long disciplineId);
}
