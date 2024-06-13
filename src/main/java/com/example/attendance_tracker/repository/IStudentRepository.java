package com.example.attendance_tracker.repository;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Student AS s " +
            "SET s.lastname = :lastname, " +
            "s.firstname = :firstname, " +
            "s.middleName = :middleName, " +
            "s.status = :status, " +
            "s.group.id = :groupId " +
            "WHERE s.studentId = :studentId")
    void update(@Param(value = "lastname") String lastname,
                @Param(value = "firstname") String firstname,
                @Param(value = "middleName") String middleName,
                @Param(value = "status") String status,
                @Param(value = "groupId") long groupId,
                @Param(value = "studentId") long studentId);

    @Transactional
    @Query("SELECT s FROM Student AS s WHERE s.group.id = :groupId")
    List<Student> findAllByGroupId(@Param(value = "groupId") long groupId);
}
