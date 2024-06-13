package com.example.attendance_tracker.repository;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.entity.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Teacher AS t " +
            "SET t.lastname = :lastname, " +
            "t.firstname = :firstname, " +
            "t.middleName = :middleName " +
            "WHERE t.teacherId = :teacherId")
    void update(@Param(value = "lastname") String lastname,
                @Param(value = "firstname") String firstname,
                @Param(value = "middleName") String middleName,
                @Param(value = "teacherId") long teacherId);
}
