package com.example.attendance_tracker.controller;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.student_request.AddStudentRequest;
import com.example.attendance_tracker.request.student_request.EditStudentRequest;
import com.example.attendance_tracker.service.service_interface.IStudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attendance_tracker/student")
@AllArgsConstructor
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@Valid @RequestBody AddStudentRequest request) throws NotFoundServiceException {
        return new ResponseEntity<>(studentService.add(request), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        studentService.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editStudent")
    public ResponseEntity<?> editStudent(@Valid @RequestBody EditStudentRequest request) throws NotFoundServiceException{
        studentService.edit(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getStudent")
    public ResponseEntity<?> getStudent(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(studentService.get(request), HttpStatus.OK);
    }

    @GetMapping("/getStudentsByGroupId")
    public ResponseEntity<?> getStudentsByGroupId(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(studentService.getAllByGroupId(request), HttpStatus.OK);
    }
}
