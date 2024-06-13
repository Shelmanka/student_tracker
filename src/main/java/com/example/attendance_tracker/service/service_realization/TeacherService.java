package com.example.attendance_tracker.service.service_realization;

import com.example.attendance_tracker.entity.Teacher;
import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.repository.ITeacherRepository;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.student_request.EditStudentRequest;
import com.example.attendance_tracker.request.teacher_request.AddTeacherRequest;
import com.example.attendance_tracker.request.teacher_request.EditTeacherRequest;
import com.example.attendance_tracker.response.teacher_response.AddTeacherResponse;
import com.example.attendance_tracker.response.teacher_response.GetTeacherResponse;
import com.example.attendance_tracker.service.service_interface.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public AddTeacherResponse add(AddTeacherRequest request){
        Teacher teacher = new Teacher(null, request.getLastname(), request.getFirstname(), request.getMiddleName());
        var save = teacherRepository.save(teacher);
        return new AddTeacherResponse(save.getTeacherId());
    }

    @Override
    public void delete(IdRequest request) throws NotFoundServiceException {
        teacherRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        teacherRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditTeacherRequest request) throws NotFoundServiceException{
        teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        teacherRepository.update(request.getLastname(), request.getFirstname(), request.getMiddleName(), request.getTeacherId());
    }

    @Override
    public GetTeacherResponse get(IdRequest request) throws NotFoundServiceException{
        teacherRepository.findById(request.getId()).orElseThrow();
        return new GetTeacherResponse(teacherRepository
                .findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("invalid teacher id")));
    }

    @Override
    public List<GetTeacherResponse> getAll(){
        return teacherRepository.findAll()
                .stream()
                .map(t -> new GetTeacherResponse(t.getTeacherId(), t.getLastname(), t.getFirstname(), t.getMiddleName()))
                .toList();
    }
}
