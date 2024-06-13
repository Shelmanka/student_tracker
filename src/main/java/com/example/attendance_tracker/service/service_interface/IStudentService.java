package com.example.attendance_tracker.service.service_interface;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.student_request.AddStudentRequest;
import com.example.attendance_tracker.request.student_request.EditStudentRequest;
import com.example.attendance_tracker.response.student_response.AddStudentResponse;
import com.example.attendance_tracker.response.student_response.GetStudentResponse;

import java.util.List;

public interface IStudentService {
    AddStudentResponse add(AddStudentRequest request) throws NotFoundServiceException;
    void delete(IdRequest request) throws NotFoundServiceException;
    void edit(EditStudentRequest request) throws NotFoundServiceException;
    GetStudentResponse get(IdRequest request) throws NotFoundServiceException;
    List<GetStudentResponse> getAllByGroupId(IdRequest request) throws NotFoundServiceException;
}
