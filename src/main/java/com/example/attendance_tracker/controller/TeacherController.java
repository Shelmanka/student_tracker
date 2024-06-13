package com.example.attendance_tracker.controller;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.teacher_request.AddTeacherRequest;
import com.example.attendance_tracker.request.teacher_request.EditTeacherRequest;
import com.example.attendance_tracker.service.service_interface.ITeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attendance_tracker/teacher")
@AllArgsConstructor
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @PostMapping("/addTeacher")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody AddTeacherRequest request) throws ServiceException{
        try {
            return new ResponseEntity<>(teacherService.add(request), HttpStatus.OK);
        } catch (Exception e){
            throw new ServiceException("this teacher is already exists");
        }
    }

    @DeleteMapping("/deleteTeacher")
    public ResponseEntity<?> deleteTeacher(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        teacherService.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editTeacher")
    public ResponseEntity<?> editTeacher(@Valid @RequestBody EditTeacherRequest request) throws NotFoundServiceException{
        teacherService.edit(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getTeacher")
    public ResponseEntity<?> getTeacher(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(teacherService.get(request), HttpStatus.OK);
    }

    @GetMapping("/getAllTeachers")
    public ResponseEntity<?> getAllTeachers(){
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }
}
