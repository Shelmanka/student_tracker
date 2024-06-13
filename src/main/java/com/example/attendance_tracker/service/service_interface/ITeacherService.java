package com.example.attendance_tracker.service.service_interface;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.teacher_request.AddTeacherRequest;
import com.example.attendance_tracker.request.teacher_request.EditTeacherRequest;
import com.example.attendance_tracker.response.teacher_response.AddTeacherResponse;
import com.example.attendance_tracker.response.teacher_response.GetTeacherResponse;

import java.util.List;

public interface ITeacherService {
    AddTeacherResponse add(AddTeacherRequest request);
    void delete(IdRequest request) throws NotFoundServiceException;
    void edit(EditTeacherRequest request) throws NotFoundServiceException;
    GetTeacherResponse get(IdRequest request) throws NotFoundServiceException;
    List<GetTeacherResponse> getAll();
}
