/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import facades.TeachersAndDisciplinesFacade;
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
public class TeachersAndDisciplinesController implements Serializable {

    private DataModel items = null;
    @EJB
    private TeachersAndDisciplinesFacade ejbFacade;

    public TeachersAndDisciplinesController() {
    }

    public String comeBack() {
        items = null;
        return "user";
    }

    public String goForward() {
        items = new ArrayDataModel(ejbFacade.findAll().toArray());
        return "TeachersAndDisciplines";
    }

    public DataModel getItems() {
        return items;
    }
}
