package com.example.attendance_tracker.repository;

import com.example.attendance_tracker.entity.AttendanceList;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendanceListRepository extends JpaRepository<AttendanceList, Long> {
    @Transactional
    @Query("SELECT l FROM AttendanceList AS l WHERE l.lesson.id = :lessonId")
    AttendanceList findAttendanceListByLessonId(@Param(value = "lessonId") long lessonId);

    @Transactional
    @Modifying
    @Query("DELETE FROM AttendanceList AS l WHERE l.lesson.id = :lessonId")
    void deleteAttendanceListByLessonId(@Param(value = "lessonId") long lessonId);
}
