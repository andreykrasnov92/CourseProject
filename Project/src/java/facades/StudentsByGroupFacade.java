/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Groups;
import entities.Students;
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
public class StudentsByGroupFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    public List<Students> findAll(String groupNumber) {
        try {
            List<Groups> groups = em.createNamedQuery("Groups.findByGroupNumber")
                    .setParameter("groupNumber", groupNumber).getResultList();
            List<Students> students = new ArrayList<Students>();
            for (Iterator<Groups> it = groups.iterator(); it.hasNext();) {
                students.addAll(it.next().getStudentsCollection());
            }
            return students;
        } catch (NoResultException e) {
            return new ArrayList<Students>();
        }
    }
}
