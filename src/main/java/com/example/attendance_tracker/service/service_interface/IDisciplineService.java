package com.example.attendance_tracker.service.service_interface;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.discipline_request.AddDisciplineRequest;
import com.example.attendance_tracker.request.discipline_request.EditDisciplineRequest;
import com.example.attendance_tracker.response.discipline_response.AddDisciplineResponse;
import com.example.attendance_tracker.response.discipline_response.GetDisciplineResponse;

import java.util.List;

public interface IDisciplineService {
    AddDisciplineResponse add(AddDisciplineRequest request);
    void delete(IdRequest request) throws NotFoundServiceException;
    void edit(EditDisciplineRequest request) throws NotFoundServiceException;
    GetDisciplineResponse get(IdRequest request) throws NotFoundServiceException;
    List<GetDisciplineResponse> getAll();
}
