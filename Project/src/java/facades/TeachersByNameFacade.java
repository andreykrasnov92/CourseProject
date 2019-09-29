/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Teachers;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 1
 */
@Stateless
public class TeachersByNameFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    public List<Teachers> findAll(String teacherName) {
        try {
            return em.createNamedQuery("Teachers.findByTeacherName")
                    .setParameter("teacherName", teacherName).getResultList();
        } catch (NoResultException e) {
            return new ArrayList<Teachers>();
        }
    }
}
