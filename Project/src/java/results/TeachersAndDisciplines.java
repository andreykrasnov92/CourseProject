/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package results;

import entities.Disciplines;
import entities.Teachers;
import java.io.Serializable;

/**
 *
 * @author 1
 */
public class TeachersAndDisciplines implements Serializable {

    private Teachers teacher;
    private Disciplines discipline;

    public TeachersAndDisciplines(Teachers teacher, Disciplines discipline) {
        this.teacher = teacher;
        this.discipline = discipline;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }
}
