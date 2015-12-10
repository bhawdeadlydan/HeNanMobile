package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/9.
 */
@Entity
@Table(name = "check", schema = "mobile", catalog = "")
public class CheckEntity {
    private String materialCode;
    private String position;
    private Timestamp time;
    private int id;
    private Integer realNum;

    @Basic
    @Column(name = "MaterialCode")
    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    @Basic
    @Column(name = "Position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "TIME")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

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
    @Column(name = "RealNum")
    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckEntity that = (CheckEntity) o;

        if (id != that.id) return false;
        if (materialCode != null ? !materialCode.equals(that.materialCode) : that.materialCode != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (realNum != null ? !realNum.equals(that.realNum) : that.realNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = materialCode != null ? materialCode.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (realNum != null ? realNum.hashCode() : 0);
        return result;
    }
}
