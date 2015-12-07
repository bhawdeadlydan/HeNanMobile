package db;

import javax.persistence.*;

/**
 * Created by richard on 2015/12/7.
 */
@Entity
@Table(name = "bom", schema = "mobile", catalog = "")
public class BomEntity {
    private int id;
    private String asnCode;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ASN_CODE")
    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BomEntity bomEntity = (BomEntity) o;

        if (id != bomEntity.id) return false;
        if (asnCode != null ? !asnCode.equals(bomEntity.asnCode) : bomEntity.asnCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (asnCode != null ? asnCode.hashCode() : 0);
        return result;
    }
}
