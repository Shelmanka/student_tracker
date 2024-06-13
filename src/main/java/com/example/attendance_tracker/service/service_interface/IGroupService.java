package com.example.attendance_tracker.service.service_interface;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.group_request.AddGroupRequest;
import com.example.attendance_tracker.request.group_request.EditGroupRequest;
import com.example.attendance_tracker.response.group_response.AddGroupResponse;
import com.example.attendance_tracker.response.group_response.GetGroupResponse;

import java.util.List;

public interface IGroupService {
    AddGroupResponse add(AddGroupRequest request);
    void delete(IdRequest request) throws NotFoundServiceException;
    void edit(EditGroupRequest request) throws NotFoundServiceException;
    GetGroupResponse get(IdRequest request) throws NotFoundServiceException;
    List<GetGroupResponse> getAll();
}
