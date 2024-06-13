package com.example.attendance_tracker;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.entity.Student;
import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.repository.IGroupRepository;
import com.example.attendance_tracker.repository.IStudentRepository;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.student_request.AddStudentRequest;
import com.example.attendance_tracker.request.student_request.EditStudentRequest;
import com.example.attendance_tracker.response.student_response.AddStudentResponse;
import com.example.attendance_tracker.response.student_response.GetStudentResponse;
import com.example.attendance_tracker.service.service_realization.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private IStudentRepository studentRepository;
    @Mock
    private IGroupRepository groupRepository;
    @InjectMocks
    private StudentService studentService;

    @Test
    void addTest() throws NotFoundServiceException {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Student student = Mockito.mock(Student.class);
        when(student.getStudentId()).thenReturn(studentId);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        when(groupRepository.findById(isNull())).thenReturn(Optional.of(new Group()));
        AddStudentRequest request = new AddStudentRequest("", "", "", "", null);
        //Act
        AddStudentResponse response = studentService.add(request);

        //Assert
        assertNotNull(response);
        assertEquals(new AddStudentResponse(studentId), response);
    }

    @Test
    void addTest_ThrowException() throws NotFoundServiceException {
        //Arrange
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid group id");
        }).when(groupRepository).findById(any());
        AddStudentRequest request = new AddStudentRequest("", "", "", "", null);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> studentService.add(request));
    }

    @Test
    void deleteTest() throws ServiceException {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(new Student()));
        IdRequest request = new IdRequest(studentId);

        //Act
        studentService.delete(request);

        //Assert
        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    void deleteTest_ThrowException() {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid student id");
        }).when(studentRepository).findById(studentId);
        IdRequest request = new IdRequest(studentId);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> studentService.delete(request));
    }

    @Test
    void editTest() throws ServiceException {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String lastname = "Ivanov";
        String firstname = "Ivan";
        String middleName = "Ivanovich";
        String status = "study";
        Group group = Mockito.mock(Group.class);
        Student student = new Student(studentId, lastname, firstname, middleName, status, group);
        String newLastname = "Petrov";
        String newFirstname = "Petr";
        String newMiddleName = "Petrovich";
        String newStatus = "academ";
        Long newGroupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Group newGroup = Mockito.mock(Group.class);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(groupRepository.findById(newGroupId)).thenReturn(Optional.of(new Group()));
        doAnswer(invocation -> {
            student.setLastname(newLastname);
            student.setFirstname(newFirstname);
            student.setMiddleName(newMiddleName);
            student.setStatus(newStatus);
            student.setGroup(newGroup);
            return 0;
        }).when(studentRepository).update(newLastname, newFirstname, newMiddleName, newStatus, newGroupId, studentId);
        EditStudentRequest request = new EditStudentRequest(studentId, newLastname, newFirstname, newMiddleName, newStatus, newGroupId);

        //Act
        studentService.edit(request);

        //Assert
        verify(studentRepository, times(1)).update(newLastname, newFirstname, newMiddleName, newStatus, newGroupId, studentId);
        assertEquals(new Student(studentId, newLastname, newFirstname, newMiddleName, newStatus, newGroup), student);
    }

    @Test
    void editTest_ThrowException_InvalidStudentId() {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid student id");
        }).when(studentRepository).findById(studentId);
        EditStudentRequest request = new EditStudentRequest(studentId, "", "", "", "", null);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> studentService.edit(request));
    }

    @Test
    void editTest_ThrowException_InvalidGroupId() {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid group id");
        }).when(studentRepository).findById(studentId);
        EditStudentRequest request = new EditStudentRequest(studentId, "", "", "", "", null);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> studentService.edit(request));
    }

    @Test
    void getTest() throws NotFoundServiceException {
        // Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        Group group = new Group();
        group.setGroupId(groupId);

        Student student = new Student(studentId, "", "", "", "", group);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        IdRequest request = new IdRequest(studentId);

        // Act
        GetStudentResponse response = studentService.get(request);

        // Assert
        assertNotNull(response);
        assertEquals(new GetStudentResponse(student), response);
    }

    @Test
    void getTest_ThrowException() {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid student id");
        }).when(studentRepository).findById(studentId);
        IdRequest request = new IdRequest(studentId);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> studentService.get(request));
    }

    @Test
    void getByGroupIdTest() throws NotFoundServiceException {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Student student_1 = new Student(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                "", "", "", "",
                new Group(groupId, ""));
        Student student_2 = new Student(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                "", "", "", "",
                new Group(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, ""));
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(new Group()));
        when(studentRepository.findAllByGroupId(groupId)).thenReturn(List.of(student_1));
        IdRequest request = new IdRequest(groupId);

        //Act
        List<GetStudentResponse> response = studentService.getAllByGroupId(request);

        //Assert
        assertNotNull(response);
        assertEquals(List.of(new GetStudentResponse(student_1)), response);
    }

    @Test
    void getByGroupIdTest_EmptyList() throws NotFoundServiceException {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Student student_1 = new Student(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                "", "", "", "",
                new Group(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, ""));
        Student student_2 = new Student(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                "", "", "", "",
                new Group(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, ""));
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(new Group()));
        when(studentRepository.findAllByGroupId(groupId)).thenReturn(List.of());
        IdRequest request = new IdRequest(groupId);

        //Act
        List<GetStudentResponse> response = studentService.getAllByGroupId(request);

        //Assert
        assertNotNull(response);
        assertEquals(List.of(), response);
    }

    @Test
    void getByGroupIdTest_ThrowException() throws NotFoundServiceException {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid group id");
        }).when(groupRepository).findById(groupId);
        IdRequest request = new IdRequest(groupId);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> studentService.getAllByGroupId(request));
    }
}