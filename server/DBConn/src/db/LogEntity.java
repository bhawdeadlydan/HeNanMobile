package db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by richard on 2015/12/6.
 */
@Entity
@Table(name = "log", schema = "mobile", catalog = "")
public class LogEntity {
    private Timestamp time;
    private String operation;
    private String note;
    private int id;

    @Basic
    @Column(name = "TIME")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "OPERATION")
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogEntity logEntity = (LogEntity) o;

        if (time != null ? !time.equals(logEntity.time) : logEntity.time != null) return false;
        if (operation != null ? !operation.equals(logEntity.operation) : logEntity.operation != null) return false;
        if (note != null ? !note.equals(logEntity.note) : logEntity.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
