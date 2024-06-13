package com.example.attendance_tracker.repository;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.entity.Lesson;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILessonRepository extends JpaRepository<Lesson, Long> {
    @Transactional
    @Query("SELECT l FROM Lesson AS l WHERE l.group.id = :groupId")
    List<Lesson> findLessonsByGroupId(@Param(value = "groupId") long groupId);

    @Transactional
    @Query("SELECT l FROM Lesson AS l WHERE l.teacher.id = :teacherId")
    List<Lesson> findLessonsByTeacherId(@Param(value = "teacherId") long teacherId);

    @Transactional
    @Modifying
    @Query("UPDATE Lesson AS l " +
            "SET l.lessonNumber = :lessonNumber, " +
            "l.teacher.id = :teacherId, " +
            "l.discipline.id = :disciplineId, " +
            "l.lessonDate = :lessonDate,  " +
            "l.group.id = :groupId " +
            "WHERE l.lessonId = :lessonId")
    void update(@Param(value = "lessonNumber") int lessonNumber,
                @Param(value = "teacherId") long teacherId,
                @Param(value = "disciplineId") long disciplineId,
                @Param(value = "lessonDate") String lessonDate,
                @Param(value = "groupId") long groupId,
                @Param(value = "lessonId") long lessonId);
}
