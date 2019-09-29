/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import facades.LoginFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 1
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private LoginFacade ejbFacade;
    private boolean admin = false;
    private boolean teacher = false;
    private boolean student = false;
    private String adLogin = "";
    private String adPassword = "";
    private String teLogin = "";
    private String tePassword = "";
    private String stLogin = "";
    private String stPassword = "";

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public LoginFacade getEjbFacade() {
        return ejbFacade;
    }

    public String getAdLogin() {
        return adLogin;
    }

    public void setAdLogin(String adLogin) {
        this.adLogin = adLogin;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    public String getTeLogin() {
        return teLogin;
    }

    public void setTeLogin(String teLogin) {
        this.teLogin = teLogin;
    }

    public String getTePassword() {
        return tePassword;
    }

    public void setTePassword(String tePassword) {
        this.tePassword = tePassword;
    }

    public String getStLogin() {
        return stLogin;
    }

    public void setStLogin(String stLogin) {
        this.stLogin = stLogin;
    }

    public String getStPassword() {
        return stPassword;
    }

    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
    }

    public String loginAdmin() {
        admin = ejbFacade.isAdmin(adLogin, adPassword);
        teacher = false;
        student = false;
        return "main";
    }

    public String loginTeacher() {
        admin = false;
        teacher = ejbFacade.isTeacher(teLogin, tePassword);
        student = false;
        return "user";
    }

    public String loginStudent() {
        admin = false;
        teacher = false;
        student = ejbFacade.isStudent(stLogin, stPassword);
        return "user";
    }

    public String logOff() {
        admin = false;
        teacher = false;
        student = false;
        adLogin = "";
        adPassword = "";
        teLogin = "";
        tePassword = "";
        stLogin = "";
        stPassword = "";
        return "index";
    }
}
