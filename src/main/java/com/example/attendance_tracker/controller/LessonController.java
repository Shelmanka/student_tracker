package com.example.attendance_tracker.controller;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.lesson_request.AddLessonRequest;
import com.example.attendance_tracker.request.lesson_request.EditLessonRequest;
import com.example.attendance_tracker.service.service_interface.ILessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attendance_tracker/lesson")
@AllArgsConstructor
public class LessonController {
    @Autowired
    private ILessonService lessonService;

    @PostMapping("/addLesson")
    public ResponseEntity<?> addLesson(@Valid @RequestBody AddLessonRequest request) throws ServiceException {
        return new ResponseEntity<>(lessonService.add(request), HttpStatus.OK);
    }

    @DeleteMapping("/deleteLesson")
    public ResponseEntity<?> deleteLesson(@Valid @RequestBody IdRequest request) throws NotFoundServiceException {
        lessonService.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editLesson")
    public ResponseEntity<?> editLesson(@Valid @RequestBody EditLessonRequest request) throws ServiceException{
        lessonService.edit(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getLesson")
    public ResponseEntity<?> getLesson(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(lessonService.get(request), HttpStatus.OK);
    }

    @GetMapping("/getLessonsByTeacherId")
    public ResponseEntity<?> getLessonsByTeacherId(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(lessonService.getAllByTeacherId(request), HttpStatus.OK);
    }

    @GetMapping("/getLessonsByGroupId")
    public ResponseEntity<?> getLessonsByGroupId(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(lessonService.getAllByGroupId(request), HttpStatus.OK);
    }
}
