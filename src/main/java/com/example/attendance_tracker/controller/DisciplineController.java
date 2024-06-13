package com.example.attendance_tracker.controller;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.discipline_request.AddDisciplineRequest;
import com.example.attendance_tracker.request.discipline_request.EditDisciplineRequest;
import com.example.attendance_tracker.service.service_interface.IDisciplineService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attendance_tracker/discipline")
@AllArgsConstructor
public class DisciplineController {
    @Autowired
    private IDisciplineService disciplineService;

    @PostMapping("/addDiscipline")
    public ResponseEntity<?> addDiscipline(@Valid @RequestBody AddDisciplineRequest request) throws ServiceException {
        try {
            return new ResponseEntity<>(disciplineService.add(request), HttpStatus.OK);
        } catch (Exception e){
            throw new ServiceException("this discipline is already exists");
        }
    }

    @DeleteMapping("/deleteDiscipline")
    public ResponseEntity<?> deleteDiscipline(@Valid @RequestBody IdRequest request) throws NotFoundServiceException {
        disciplineService.delete(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editDiscipline")
    public ResponseEntity<?> editDiscipline(@Valid @RequestBody EditDisciplineRequest request) throws NotFoundServiceException{
        disciplineService.edit(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getDiscipline")
    public ResponseEntity<?> getDiscipline(@Valid @RequestBody IdRequest request) throws NotFoundServiceException{
        return new ResponseEntity<>(disciplineService.get(request), HttpStatus.OK);
    }

    @GetMapping("/getAllDisciplines")
    public ResponseEntity<?> getAllDisciplines(){
        return new ResponseEntity<>(disciplineService.getAll(), HttpStatus.OK);
    }
}
