package com.example.attendance_tracker.service.service_interface;

import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.lesson_request.AddLessonRequest;
import com.example.attendance_tracker.request.lesson_request.EditLessonRequest;
import com.example.attendance_tracker.response.lesson_response.AddLessonResponse;
import com.example.attendance_tracker.response.lesson_response.GetLessonResponse;

import java.util.List;

public interface ILessonService {
    AddLessonResponse add(AddLessonRequest request) throws ServiceException;
    void delete(IdRequest request) throws NotFoundServiceException;
    void edit(EditLessonRequest request) throws ServiceException;
    GetLessonResponse get(IdRequest request) throws NotFoundServiceException;
    List<GetLessonResponse> getAllByGroupId(IdRequest request) throws NotFoundServiceException;
    List<GetLessonResponse> getAllByTeacherId(IdRequest request) throws NotFoundServiceException;
}
