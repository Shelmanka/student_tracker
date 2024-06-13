package com.example.attendance_tracker.service.service_realization;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.repository.IGroupRepository;
import com.example.attendance_tracker.repository.IStudentRepository;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.group_request.AddGroupRequest;
import com.example.attendance_tracker.request.group_request.EditGroupRequest;
import com.example.attendance_tracker.response.group_response.AddGroupResponse;
import com.example.attendance_tracker.response.group_response.GetGroupResponse;
import com.example.attendance_tracker.service.service_interface.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService implements IGroupService {
    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public AddGroupResponse add(AddGroupRequest request){
        Group group = new Group(null, request.getGroupName());
        var save = groupRepository.save(group);
        return new AddGroupResponse(save.getGroupId());
    }

    @Override
    public void delete(IdRequest request) throws NotFoundServiceException {
        groupRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        groupRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditGroupRequest request) throws NotFoundServiceException{
        groupRepository.findById(request.getGroupId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        groupRepository.update(request.getGroupName(), request.getGroupId());
    }

    @Override
    public GetGroupResponse get(IdRequest request) throws NotFoundServiceException{
        return new GetGroupResponse(groupRepository
                .findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("invalid group id")));
    }

    @Override
    public List<GetGroupResponse> getAll(){
        return groupRepository.findAll().stream()
                .map(g -> new GetGroupResponse(g.getGroupId(), g.getGroupName()))
                .toList();
    }
}
