/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "teachers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teachers.findAll", query = "SELECT t FROM Teachers t"),
    @NamedQuery(name = "Teachers.findByTeacherId", query = "SELECT t FROM Teachers t WHERE t.teacherId = :teacherId"),
    @NamedQuery(name = "Teachers.findByTeacherName", query = "SELECT t FROM Teachers t WHERE t.teacherName = :teacherName"),
    @NamedQuery(name = "Teachers.findByTeacherLogin", query = "SELECT t FROM Teachers t WHERE t.teacherLogin = :teacherLogin"),
    @NamedQuery(name = "Teachers.findByTeacherPassword", query = "SELECT t FROM Teachers t WHERE t.teacherPassword = :teacherPassword"),
    @NamedQuery(name = "Teachers.findByAcademicDegree", query = "SELECT t FROM Teachers t WHERE t.academicDegree = :academicDegree"),
    @NamedQuery(name = "Teachers.findByAcademicRank", query = "SELECT t FROM Teachers t WHERE t.academicRank = :academicRank"),
    @NamedQuery(name = "Teachers.findByDepartment", query = "SELECT t FROM Teachers t WHERE t.department = :department"),
    @NamedQuery(name = "TeachersAndDisciplines", query = "SELECT NEW results.TeachersAndDisciplines(t, d) FROM Teachers t JOIN t.disciplinesCollection d")})
public class Teachers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "teacher_id")
    private Integer teacherId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "teacher_name")
    private String teacherName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "teacher_login")
    private String teacherLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "teacher_password")
    private String teacherPassword;
    @Size(max = 45)
    @Column(name = "academic_degree")
    private String academicDegree;
    @Size(max = 45)
    @Column(name = "academic_rank")
    private String academicRank;
    @Size(max = 45)
    @Column(name = "department")
    private String department;
    @ManyToMany(mappedBy = "teachersCollection")
    private Collection<Disciplines> disciplinesCollection;

    public Teachers() {
    }

    public Teachers(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Teachers(Integer teacherId, String teacherName, String teacherLogin, String teacherPassword) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherLogin = teacherLogin;
        this.teacherPassword = Integer.toString(teacherPassword.hashCode());
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(String teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public String getTeacherPasswordHash() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = Integer.toString(teacherPassword.hashCode());
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(String academicRank) {
        this.academicRank = academicRank;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @XmlTransient
    public Collection<Disciplines> getDisciplinesCollection() {
        return disciplinesCollection;
    }

    public void setDisciplinesCollection(Collection<Disciplines> disciplinesCollection) {
        this.disciplinesCollection = disciplinesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teacherId != null ? teacherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teachers)) {
            return false;
        }
        Teachers other = (Teachers) object;
        if ((this.teacherId == null && other.teacherId != null) || (this.teacherId != null && !this.teacherId.equals(other.teacherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return teacherId + ". " + teacherName;
    }
}
