package com.example.attendance_tracker.controller;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.group_request.AddGroupRequest;
import com.example.attendance_tracker.request.group_request.EditGroupRequest;
import com.example.attendance_tracker.service.service_interface.IGroupService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("attendance_tracker/group")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @PostMapping("/addGroup")
    public ResponseEntity<?> addGroup(@Valid @RequestBody AddGroupRequest request) throws ServiceException {
        try{
            return new ResponseEntity<>(groupService.add(request), HttpStatus.OK);
        } catch (Exception e){
            throw new ServiceException("this group already exists");
        }
    }

    @DeleteMapping(value = "/deleteGroup")
    public ResponseEntity<?> deleteGroup(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        groupService.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/editGroup")
    public ResponseEntity<?> editGroup(@Valid @RequestBody EditGroupRequest request) throws NotFoundServiceException{
        groupService.edit(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getGroup")
    public ResponseEntity<?> getGroup(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(groupService.get(request), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllGroups")
    public ResponseEntity<?> getAllGroups(){
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }
}
