/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import results.TeachersAndDisciplines;

/**
 *
 * @author 1
 */
@Stateless
public class TeachersAndDisciplinesFacade {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    public List<TeachersAndDisciplines> findAll() {
        try {
            return em.createNamedQuery("TeachersAndDisciplines")
                    .getResultList();
        } catch (NoResultException e) {
            return new ArrayList<TeachersAndDisciplines>();
        }
    }
}
