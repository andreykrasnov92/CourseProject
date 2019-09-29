/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Teachers;
import facades.DisciplinesByTeacherFacade;
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
public class DisciplinesByTeacherController implements Serializable {

    private DataModel items = null;
    @EJB
    private DisciplinesByTeacherFacade ejbFacade;
    private Teachers teacher;

    public DisciplinesByTeacherController() {
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public String comeBack() {
        this.teacher = null;
        items = null;
        return "user";
    }

    public String goForward() {
        try {
            items = new ArrayDataModel(ejbFacade.findAll(teacher.getTeacherName()).toArray());
        } catch (NullPointerException e) {
        }
        return "DisciplinesByTeacher";
    }

    public DataModel getItems() {
        return items;
    }
}
