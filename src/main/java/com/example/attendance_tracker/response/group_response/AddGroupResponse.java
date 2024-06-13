package com.example.attendance_tracker.response.group_response;

import com.example.attendance_tracker.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddGroupResponse {
    private long groupId;

    public AddGroupResponse(Group group){
        this.groupId = group.getGroupId();
    }
}
