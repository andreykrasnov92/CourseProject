/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Groups;
import facades.StudentsByGroupFacade;
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
public class StudentsByGroupController implements Serializable {

    private DataModel items = null;
    @EJB
    private StudentsByGroupFacade ejbFacade;
    private Groups group;

    public StudentsByGroupController() {
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public String comeBack() {
        this.group = null;
        items = null;
        return "user";
    }

    public String goForward() {
        try {
            items = new ArrayDataModel(ejbFacade.findAll(group.getGroupNumber()).toArray());
        } catch (NullPointerException e) {
        }
        return "StudentsByGroup";
    }

    public DataModel getItems() {
        return items;
    }
}
