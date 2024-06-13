package com.example.attendance_tracker.service.service_realization;

import com.example.attendance_tracker.entity.Discipline;
import com.example.attendance_tracker.exception.service_exception.NotFoundServiceException;
import com.example.attendance_tracker.repository.IDisciplineRepository;
import com.example.attendance_tracker.request.IdRequest;
import com.example.attendance_tracker.request.discipline_request.AddDisciplineRequest;
import com.example.attendance_tracker.request.discipline_request.EditDisciplineRequest;
import com.example.attendance_tracker.request.teacher_request.EditTeacherRequest;
import com.example.attendance_tracker.response.discipline_response.AddDisciplineResponse;
import com.example.attendance_tracker.response.discipline_response.GetDisciplineResponse;
import com.example.attendance_tracker.service.service_interface.IDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService implements IDisciplineService {
    @Autowired
    private IDisciplineRepository disciplineRepository;

    @Override
    public AddDisciplineResponse add(AddDisciplineRequest request){
        Discipline discipline = new Discipline(null, request.getDisciplineName());
        var save = disciplineRepository.save(discipline);
        return new AddDisciplineResponse(save.getDisciplineId());
    }

    @Override
    public void delete(IdRequest request) throws NotFoundServiceException {
        disciplineRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid discipline id"));
        disciplineRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditDisciplineRequest request) throws NotFoundServiceException{
        disciplineRepository.findById(request.getDisciplineId()).orElseThrow(() -> new NotFoundServiceException("invalid discipline id"));
        disciplineRepository.update(request.getDisciplineName(), request.getDisciplineId());
    }

    @Override
    public GetDisciplineResponse get(IdRequest request) throws NotFoundServiceException{
        disciplineRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid discipline id"));
        return new GetDisciplineResponse(disciplineRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("invalid discipline id")));
    }

    @Override
    public List<GetDisciplineResponse> getAll(){
        return disciplineRepository.findAll()
                .stream()
                .map(d -> new GetDisciplineResponse(d.getDisciplineId(), d.getDisciplineName()))
                .toList();
    }
}
