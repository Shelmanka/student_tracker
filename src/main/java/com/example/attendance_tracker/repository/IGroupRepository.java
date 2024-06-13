package com.example.attendance_tracker.repository;

import com.example.attendance_tracker.entity.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupRepository extends JpaRepository<Group, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Group AS g " +
            "SET g.groupName = :groupName " +
            "WHERE g.groupId = :groupId")
    void update(@Param(value = "groupName") String groupName,
                @Param(value = "groupId") long groupId);
}
