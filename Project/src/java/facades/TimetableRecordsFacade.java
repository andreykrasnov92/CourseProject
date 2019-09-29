/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.TimetableRecords;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 1
 */
@Stateless
public class TimetableRecordsFacade extends AbstractFacade<TimetableRecords> {

    @PersistenceContext(unitName = "TNPPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TimetableRecordsFacade() {
        super(TimetableRecords.class);
    }
}
