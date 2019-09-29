/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Groups;
import entities.TimetableRecords;
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
public class TimetableByGroupFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    public List<TimetableRecords> findAll(String groupNumber) {
        try {
            List<Groups> groups = em.createNamedQuery("Groups.findByGroupNumber")
                    .setParameter("groupNumber", groupNumber).getResultList();
            List<TimetableRecords> timetable = new ArrayList<TimetableRecords>();
            for (Iterator<Groups> it = groups.iterator(); it.hasNext();) {
                timetable.addAll(it.next().getTimetableRecordsCollection());
            }
            return timetable;
        } catch (NoResultException e) {
            return new ArrayList<TimetableRecords>();
        }
    }
}
