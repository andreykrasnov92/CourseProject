/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Disciplines;
import facades.TeachersByDisciplineFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

/**
 *
 * @author 1
 */
@ManagedBean
@SessionScoped
public class TeachersByDisciplineController implements Serializable {

    private DataModel items = null;
    @EJB
    private TeachersByDisciplineFacade ejbFacade;
    private Disciplines discipline;

    public TeachersByDisciplineController() {
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    public String comeBack() {
        this.discipline = null;
        items = null;
        return "user";
    }

    public String goForward() {
        try {
            items = new ArrayDataModel(ejbFacade
                    .findAll(discipline.getDisciplineName(), discipline.getLessonType()).toArray());
        } catch (NullPointerException e) {
        }
        return "TeachersByDiscipline";
    }

    public DataModel getItems() {
        return items;
    }
}
