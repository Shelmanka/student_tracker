package com.example.attendance_tracker.response.discipline_response;

import com.example.attendance_tracker.entity.Discipline;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetDisciplineResponse {
    private long disciplineId;
    private String disciplineName;

    public GetDisciplineResponse(Discipline discipline){
        this(discipline.getDisciplineId(), discipline.getDisciplineName());
    }
}
