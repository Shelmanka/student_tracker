package com.example.attendance_tracker.service.service_realization;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.entity.Student;
import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.repository.IGroupRepository;
import com.example.attendance_tracker.repository.IStudentRepository;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.student_request.AddStudentRequest;
import com.example.attendance_tracker.request.student_request.EditStudentRequest;
import com.example.attendance_tracker.response.group_response.GetGroupResponse;
import com.example.attendance_tracker.response.student_response.AddStudentResponse;
import com.example.attendance_tracker.response.student_response.GetStudentResponse;
import com.example.attendance_tracker.service.service_interface.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public AddStudentResponse add(AddStudentRequest request) throws NotFoundServiceException {
        groupRepository.findById(request.getGroupId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        Student student = new Student(null,
                request.getLastname(),
                request.getFirstname(),
                request.getMiddleName(),
                request.getStatus(),
                new Group(request.getGroupId(), null));
        var save = studentRepository.save(student);
        return new AddStudentResponse(save.getStudentId());
    }

    @Override
    public void delete(IdRequest request) throws NotFoundServiceException{
        studentRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid student id"));
        studentRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditStudentRequest request) throws NotFoundServiceException{
        studentRepository.findById(request.getStudentId()).orElseThrow(() -> new NotFoundServiceException("invalid student id"));
        groupRepository.findById(request.getGroupId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        studentRepository.update(request.getLastname(),
                request.getFirstname(),
                request.getMiddleName(),
                request.getStatus(),
                request.getGroupId(),
                request.getStudentId());
    }

    @Override
    public GetStudentResponse get(IdRequest request) throws NotFoundServiceException{
        return new GetStudentResponse(studentRepository
                .findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid student id")));
    }

    @Override
    public List<GetStudentResponse> getAllByGroupId(IdRequest request) throws NotFoundServiceException{
        groupRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        return studentRepository.findAllByGroupId(request.getId())
                .stream()
                .map(s -> new GetStudentResponse(s.getStudentId(), s.getLastname(), s.getFirstname(), s.getMiddleName(), s.getStatus(), new GetGroupResponse(s.getGroup())))
                .toList();
    }
}
