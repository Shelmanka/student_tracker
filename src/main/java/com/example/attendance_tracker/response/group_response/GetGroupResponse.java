package com.example.attendance_tracker.response.group_response;

import com.example.attendance_tracker.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor
public class GetGroupResponse {
    private long groupId;
    private String groupName;

    public GetGroupResponse(Group group){
        this(group.getGroupId(), group.getGroupName());
    }
}
