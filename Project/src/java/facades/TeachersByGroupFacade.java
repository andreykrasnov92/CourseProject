/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Disciplines;
import entities.Groups;
import entities.Teachers;
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
public class TeachersByGroupFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    public List<Teachers> findAll(String groupNumber) {
        try {
            List<Groups> groups = em.createNamedQuery("Groups.findByGroupNumber")
                    .setParameter("groupNumber", groupNumber).getResultList();
            List<TimetableRecords> timetable = new ArrayList<TimetableRecords>();
            for (Iterator<Groups> it = groups.iterator(); it.hasNext();) {
                timetable.addAll(it.next().getTimetableRecordsCollection());
            }
            List<Disciplines> disciplines = new ArrayList<Disciplines>();
            for (Iterator<TimetableRecords> it = timetable.iterator(); it.hasNext();) {
                disciplines.add(it.next().getDisciplineId());
            }
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
