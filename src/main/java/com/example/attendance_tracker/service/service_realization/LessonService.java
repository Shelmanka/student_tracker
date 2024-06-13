package com.example.attendance_tracker.service.service_realization;

import com.example.attendance_tracker.entity.*;
import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.repository.*;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.lesson_request.AddLessonRequest;
import com.example.attendance_tracker.request.lesson_request.EditLessonRequest;
import com.example.attendance_tracker.response.lesson_response.AddLessonResponse;
import com.example.attendance_tracker.response.lesson_response.GetLessonResponse;
import com.example.attendance_tracker.service.service_interface.ILessonService;
import com.example.attendance_tracker.service.service_interface.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService implements ILessonService {
    @Autowired
    private ILessonRepository lessonRepository;
    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private ITeacherRepository teacherRepository;
    @Autowired
    private IDisciplineRepository disciplineRepository;
    @Autowired
    private IAttendanceListRepository attendanceListRepository;

    @Override
    public AddLessonResponse add(AddLessonRequest request) throws ServiceException {
        teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        disciplineRepository.findById(request.getDisciplineId()).orElseThrow(() -> new NotFoundServiceException("invalid discipline id"));
        groupRepository.findById(request.getGroupId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        if(request.getAttendanceList() != null){
            List<Long> attendanceList = request.getAttendanceList();
            for(var id : attendanceList){
                Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundServiceException("invalid student id"));
                Long groupId = student.getGroup().getGroupId();
                if(groupId != request.getGroupId()){
                    throw new ServiceException("all students from attendance list should be from group with id " + request.getGroupId());
                }
            }
        }
        Lesson lesson = new Lesson(null,
                request.getLessonDate(),
                request.getLessonNumber(),
                new Teacher(request.getTeacherId(), null, null, null),
                new Discipline(request.getDisciplineId(), null),
                new Group(request.getGroupId(), null)
                );
        var lessonSave = lessonRepository.save(lesson);
        if (request.getAttendanceList() != null) {
            AttendanceList attendanceList = new AttendanceList(
                    null, lessonSave, request.getAttendanceList().isEmpty()
                    ? new ArrayList<>()
                    : request.getAttendanceList().stream()
                    .map(o -> new Student(o, null, null, null, null, null))
                    .toList()
            );
            attendanceListRepository.save(attendanceList);
        }
        return new AddLessonResponse(lessonSave);
    }

    @Override
    public void delete(IdRequest request) throws NotFoundServiceException{
        lessonRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid lesson id"));
        attendanceListRepository.deleteAttendanceListByLessonId(request.getId());
        lessonRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditLessonRequest request) throws ServiceException{
        lessonRepository.findById(request.getLessonId()).orElseThrow(() -> new NotFoundServiceException("invalid lesson id"));
        groupRepository.findById(request.getGroupId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        disciplineRepository.findById(request.getDisciplineId()).orElseThrow(() -> new NotFoundServiceException("invalid discipline id"));
        teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        if(request.getAttendanceList() != null){
            List<Long> attendanceList = request.getAttendanceList();
            for(var id : attendanceList){
                Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundServiceException("invalid student id"));
                Long groupId = student.getGroup().getGroupId();
                if(groupId != request.getGroupId()){
                    throw new ServiceException("all students from attendance list should be from group with id " + request.getGroupId());
                }
            }
        }
        lessonRepository.update(
                request.getLessonNumber(),
                request.getTeacherId(),
                request.getDisciplineId(),
                request.getLessonDate(),
                request.getGroupId(),
                request.getLessonId()
        );
        Lesson lesson = lessonRepository.findById(request.getLessonId()).orElseThrow();
        if (attendanceListRepository.findAttendanceListByLessonId(lesson.getLessonId()) != null){
            attendanceListRepository.deleteAttendanceListByLessonId(lesson.getLessonId());
        }
        if(request.getAttendanceList() != null){
            AttendanceList attendanceList = new AttendanceList(
                    null,
                    lesson,
                    request.getAttendanceList().isEmpty()
                            ? new ArrayList<>()
                            :request.getAttendanceList().stream()
                            .map(s -> new Student(s, null, null, null, null, null))
                            .toList()
            );
            attendanceListRepository.save(attendanceList);
        }
    }

    @Override
    public GetLessonResponse get(IdRequest request) throws NotFoundServiceException{
        return new GetLessonResponse(
                lessonRepository.findById(request.getId())
                        .orElseThrow(() -> new NotFoundServiceException("invalid lesson id")),
                attendanceListRepository.findAttendanceListByLessonId(request.getId())
        );
    }

    @Override
    public List<GetLessonResponse> getAllByTeacherId(IdRequest request) throws NotFoundServiceException{
        teacherRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        return lessonRepository.findLessonsByTeacherId(request.getId()).stream()
                .map(l -> new GetLessonResponse(l, attendanceListRepository.findAttendanceListByLessonId(l.getLessonId())))
                .toList();
    }

    @Override
    public List<GetLessonResponse> getAllByGroupId(IdRequest request) throws NotFoundServiceException{
        groupRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        return lessonRepository.findLessonsByGroupId(request.getId()).stream()
                .map(l -> new GetLessonResponse(l, attendanceListRepository.findAttendanceListByLessonId(l.getLessonId())))
                .toList();
    }
}
