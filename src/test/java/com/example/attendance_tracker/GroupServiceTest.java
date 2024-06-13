package com.example.attendance_tracker;

import com.example.attendance_tracker.entity.Group;
import com.example.attendance_tracker.entity.Student;
import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.exception.service_exception.ServiceException;
import com.example.attendance_tracker.repository.IGroupRepository;
import com.example.attendance_tracker.repository.IStudentRepository;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.group_request.AddGroupRequest;
import com.example.attendance_tracker.request.group_request.EditGroupRequest;
import com.example.attendance_tracker.response.group_response.AddGroupResponse;
import com.example.attendance_tracker.response.group_response.GetGroupResponse;
import com.example.attendance_tracker.service.service_realization.GroupService;
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
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {
    @Mock
    private IGroupRepository groupRepository;
    @Mock
    private IStudentRepository studentRepository;
    @InjectMocks
    private GroupService groupService;

    @Test
    void addTest() {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String name = "name";
        Group group = new Group(groupId, name);
        when(groupRepository.save(any(Group.class))).thenReturn(group);
        AddGroupRequest request = new AddGroupRequest(name);

        //Act
        AddGroupResponse response = groupService.add(request);

        //Assert
        assertNotNull(response);
        assertEquals(new AddGroupResponse(groupId), response);
    }

    @Test
    void deleteTest() throws ServiceException {
        // Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Group group = new Group();
        group.setGroupId(groupId);

        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        //when(studentRepository.findAllByGroupId(groupId)).thenReturn(List.of());
        doNothing().when(groupRepository).deleteById(groupId);
        IdRequest request = new IdRequest(groupId);

        // Act
        groupService.delete(request);

        // Assert
        verify(groupRepository, times(1)).deleteById(groupId);
    }

    @Test
    void deleteTest_ThrowException_InvalidGroupId() {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid group id");
        }).when(groupRepository).findById(groupId);
        IdRequest request = new IdRequest(groupId);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> groupService.delete(request));
    }

    @Test
    void editTest() throws ServiceException {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String name = "name";
        Group group = new Group(groupId, name);
        String newName = "newName";
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        doAnswer(invocation -> {
            group.setGroupName(newName);
            return 0;
        }).when(groupRepository).update(newName, groupId);
        EditGroupRequest request = new EditGroupRequest(groupId, newName);

        //Act
        groupService.edit(request);

        //Assert
        verify(groupRepository, times(1)).update(newName, groupId);
        assertEquals(new Group(groupId, newName), group);
    }

    @Test
    void editTest_ThrowException() {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String newName = "newName";
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid group id");
        }).when(groupRepository).findById(groupId);
        EditGroupRequest request = new EditGroupRequest(groupId, newName);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> groupService.edit(request));
    }

    @Test
    void getByIdTest() throws NotFoundServiceException {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String name = "name";
        Group studentGroup = new Group(groupId, name);
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(studentGroup));
        IdRequest request = new IdRequest(groupId);

        //Act
        GetGroupResponse response = groupService.get(request);

        //Assert
        assertNotNull(response);
        assertEquals(new GetGroupResponse(groupId, name), response);
    }

    @Test
    void getByIdTest_ThrowException() {
        //Arrange
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundServiceException("invalid group id");
        }).when(groupRepository).findById(groupId);
        IdRequest request = new IdRequest(groupId);

        //Assert
        assertThrows(NotFoundServiceException.class, () -> groupService.get(request));
    }
}
