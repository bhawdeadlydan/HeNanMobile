package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/20.
 */
@Entity
@Table(name = "stagingsite", schema = "mobile", catalog = "")
public class StagingsiteEntity {
    private int id;
    private String applyPerson;
    private String constructPos;
    private String constructUnit;
    private Timestamp time;
    private String materialCode;
    private Integer num;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ApplyPerson")
    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    @Basic
    @Column(name = "ConstructPos")
    public String getConstructPos() {
        return constructPos;
    }

    public void setConstructPos(String constructPos) {
        this.constructPos = constructPos;
    }

    @Basic
    @Column(name = "ConstructUnit")
    public String getConstructUnit() {
        return constructUnit;
    }

    public void setConstructUnit(String constructUnit) {
        this.constructUnit = constructUnit;
    }

    @Basic
    @Column(name = "Time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "MaterialCode")
    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    @Basic
    @Column(name = "Num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StagingsiteEntity that = (StagingsiteEntity) o;

        if (id != that.id) return false;
        if (applyPerson != null ? !applyPerson.equals(that.applyPerson) : that.applyPerson != null) return false;
        if (constructPos != null ? !constructPos.equals(that.constructPos) : that.constructPos != null) return false;
        if (constructUnit != null ? !constructUnit.equals(that.constructUnit) : that.constructUnit != null)
            return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (materialCode != null ? !materialCode.equals(that.materialCode) : that.materialCode != null) return false;
        if (num != null ? !num.equals(that.num) : that.num != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (applyPerson != null ? applyPerson.hashCode() : 0);
        result = 31 * result + (constructPos != null ? constructPos.hashCode() : 0);
        result = 31 * result + (constructUnit != null ? constructUnit.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (materialCode != null ? materialCode.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        return result;
    }
}
