/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author 1
 */
@Embeddable
public class TimetableRecordsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "group_id")
    private int groupId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "week_parity")
    private int weekParity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "weekday")
    private String weekday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pair_number")
    private int pairNumber;

    public TimetableRecordsPK() {
    }

    public TimetableRecordsPK(int groupId, int weekParity, String weekday, int pairNumber) {
        this.groupId = groupId;
        this.weekParity = weekParity;
        this.weekday = weekday;
        this.pairNumber = pairNumber;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getWeekParity() {
        return weekParity;
    }

    public void setWeekParity(int weekParity) {
        this.weekParity = weekParity;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public int getPairNumber() {
        return pairNumber;
    }

    public void setPairNumber(int pairNumber) {
        this.pairNumber = pairNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) groupId;
        hash += (int) weekParity;
        hash += (weekday != null ? weekday.hashCode() : 0);
        hash += (int) pairNumber;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimetableRecordsPK)) {
            return false;
        }
        TimetableRecordsPK other = (TimetableRecordsPK) object;
        if (this.groupId != other.groupId) {
            return false;
        }
        if (this.weekParity != other.weekParity) {
            return false;
        }
        if ((this.weekday == null && other.weekday != null) || (this.weekday != null && !this.weekday.equals(other.weekday))) {
            return false;
        }
        if (this.pairNumber != other.pairNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "гр." + groupId + ", " + weekday + ((weekParity % 2 == 0) ? " " : " не") + "чётной недели, " + pairNumber + "-я пара";
    }
}
