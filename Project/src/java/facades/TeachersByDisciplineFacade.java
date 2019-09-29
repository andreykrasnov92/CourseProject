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
public class TeachersByDisciplineFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    public List<Teachers> findAll(String disciplineName, String lessonType) {
        try {
            List<Disciplines> disciplines = em.createNamedQuery("DisciplinesByNameAndType")
                    .setParameter("disciplineName", disciplineName)
                    .setParameter("lessonType", lessonType).getResultList();
            List<Teachers> teachers = new ArrayList<Teachers>();
            for (Iterator<Disciplines> it = disciplines.iterator(); it.hasNext();) {
                teachers.addAll(it.next().getTeachersCollection());
            }
            return teachers;
        } catch (NoResultException e) {
            return new ArrayList<Teachers>();
        }
    }
}
