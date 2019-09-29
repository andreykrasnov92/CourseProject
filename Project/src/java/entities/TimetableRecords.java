/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 1
 */
@Entity
@Table(name = "timetable_records")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimetableRecords.findAll", query = "SELECT t FROM TimetableRecords t"),
    @NamedQuery(name = "TimetableRecords.findByGroupId", query = "SELECT t FROM TimetableRecords t WHERE t.timetableRecordsPK.groupId = :groupId"),
    @NamedQuery(name = "TimetableRecords.findByWeekParity", query = "SELECT t FROM TimetableRecords t WHERE t.timetableRecordsPK.weekParity = :weekParity"),
    @NamedQuery(name = "TimetableRecords.findByWeekday", query = "SELECT t FROM TimetableRecords t WHERE t.timetableRecordsPK.weekday = :weekday"),
    @NamedQuery(name = "TimetableRecords.findByPairNumber", query = "SELECT t FROM TimetableRecords t WHERE t.timetableRecordsPK.pairNumber = :pairNumber"),
    @NamedQuery(name = "TimetableRecords.findByRoomNumber", query = "SELECT t FROM TimetableRecords t WHERE t.roomNumber = :roomNumber")})
public class TimetableRecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TimetableRecordsPK timetableRecordsPK;
    @Size(max = 45)
    @Column(name = "room_number")
    private String roomNumber;
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groups groups;
    @JoinColumn(name = "discipline_id", referencedColumnName = "discipline_id")
    @ManyToOne(optional = false)
    private Disciplines disciplineId;

    public TimetableRecords() {
    }

    public TimetableRecords(TimetableRecordsPK timetableRecordsPK) {
        this.timetableRecordsPK = timetableRecordsPK;
    }

    public TimetableRecords(int groupId, int weekParity, String weekday, int pairNumber) {
        this.timetableRecordsPK = new TimetableRecordsPK(groupId, weekParity, weekday, pairNumber);
    }

    public TimetableRecordsPK getTimetableRecordsPK() {
        return timetableRecordsPK;
    }

    public void setTimetableRecordsPK(TimetableRecordsPK timetableRecordsPK) {
        this.timetableRecordsPK = timetableRecordsPK;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Disciplines getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Disciplines disciplineId) {
        this.disciplineId = disciplineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timetableRecordsPK != null ? timetableRecordsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimetableRecords)) {
            return false;
        }
        TimetableRecords other = (TimetableRecords) object;
        if ((this.timetableRecordsPK == null && other.timetableRecordsPK != null) || (this.timetableRecordsPK != null && !this.timetableRecordsPK.equals(other.timetableRecordsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return timetableRecordsPK.toString();
    }
}
