/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1
 */
@Entity
@Table(name = "disciplines")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplines.findAll", query = "SELECT d FROM Disciplines d"),
    @NamedQuery(name = "Disciplines.findByDisciplineId", query = "SELECT d FROM Disciplines d WHERE d.disciplineId = :disciplineId"),
    @NamedQuery(name = "Disciplines.findByDisciplineName", query = "SELECT d FROM Disciplines d WHERE d.disciplineName = :disciplineName"),
    @NamedQuery(name = "Disciplines.findByLessonType", query = "SELECT d FROM Disciplines d WHERE d.lessonType = :lessonType"),
    @NamedQuery(name = "DisciplinesByNameAndType", query = "SELECT d FROM Disciplines d WHERE d.disciplineName = :disciplineName AND d.lessonType = :lessonType")})
public class Disciplines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "discipline_id")
    private Integer disciplineId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "discipline_name")
    private String disciplineName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lesson_type")
    private String lessonType;
    @JoinTable(name = "teachers_and_disciplines", joinColumns = {
        @JoinColumn(name = "discipline_id", referencedColumnName = "discipline_id")}, inverseJoinColumns = {
        @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")})
    @ManyToMany
    private Collection<Teachers> teachersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplineId")
    private Collection<TimetableRecords> timetableRecordsCollection;

    public Disciplines() {
    }

    public Disciplines(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Disciplines(Integer disciplineId, String disciplineName, String lessonType) {
        this.disciplineId = disciplineId;
        this.disciplineName = disciplineName;
        this.lessonType = lessonType;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    @XmlTransient
    public Collection<Teachers> getTeachersCollection() {
        return teachersCollection;
    }

    public void setTeachersCollection(Collection<Teachers> teachersCollection) {
        this.teachersCollection = teachersCollection;
    }

    @XmlTransient
    public Collection<TimetableRecords> getTimetableRecordsCollection() {
        return timetableRecordsCollection;
    }

    public void setTimetableRecordsCollection(Collection<TimetableRecords> timetableRecordsCollection) {
        this.timetableRecordsCollection = timetableRecordsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disciplineId != null ? disciplineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplines)) {
            return false;
        }
        Disciplines other = (Disciplines) object;
        if ((this.disciplineId == null && other.disciplineId != null) || (this.disciplineId != null && !this.disciplineId.equals(other.disciplineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return disciplineId + ". " + disciplineName + " (" + lessonType + ")";
    }
}
