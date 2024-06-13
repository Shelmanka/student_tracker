package com.example.attendance_tracker.response.discipline_response;

import com.example.attendance_tracker.entity.Discipline;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddDisciplineResponse {
    private long disciplineId;

    public AddDisciplineResponse(Discipline discipline){
        this.disciplineId = discipline.getDisciplineId();
    }
}
