/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Administrator;
import entities.Students;
import entities.Teachers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 1
 */
@Stateless
public class LoginFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public boolean isAdmin(String login, String password) {
        Query query = em.createNamedQuery("Administrator.findByAdministratorLogin");
        query.setParameter("administratorLogin", login);
        try {
            Administrator admin = (Administrator) query.getSingleResult();
            if (admin.getAdministratorPasswordHash().equals(Integer.toString(password.hashCode()))) {
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean isTeacher(String login, String password) {
        Query query = em.createNamedQuery("Teachers.findByTeacherLogin");
        query.setParameter("teacherLogin", login);
        try {
            Teachers teacher = (Teachers) query.getSingleResult();
            if (teacher.getTeacherPasswordHash().equals(Integer.toString(password.hashCode()))) {
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean isStudent(String login, String password) {
        Query query = em.createNamedQuery("Students.findByStudentLogin");
        query.setParameter("studentLogin", login);
        try {
            Students student = (Students) query.getSingleResult();
            if (student.getStudentPasswordHash().equals(Integer.toString(password.hashCode()))) {
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        }
    }
}
