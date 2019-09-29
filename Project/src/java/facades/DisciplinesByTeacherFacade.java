/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Disciplines;
import entities.Teachers;
import java.util.ArrayList;
import java.util.Iterator;
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
public class DisciplinesByTeacherFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    public List<Disciplines> findAll(String teacherName) {
        try {
            List<Teachers> teachers = (List<Teachers>) em.createNamedQuery("Teachers.findByTeacherName")
                    .setParameter("teacherName", teacherName).getResultList();
            List<Disciplines> disciplines = new ArrayList<Disciplines>();
            for (Iterator<Teachers> it = teachers.iterator(); it.hasNext();) {
                disciplines.addAll(it.next().getDisciplinesCollection());
            }
            return disciplines;
        } catch (NoResultException e) {
            return new ArrayList<Disciplines>();
        }
    }
}
