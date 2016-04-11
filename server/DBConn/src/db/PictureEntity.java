package db;

import javax.persistence.*;

/**
 * Created by richard on 2016/4/11.
 */
@Entity
@Table(name = "picture", schema = "mobile", catalog = "")
public class PictureEntity {
    private int id;
    private String url;
    private String applyDocCode;

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
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "APPLY_DOC_CODE")
    public String getApplyDocCode() {
        return applyDocCode;
    }

    public void setApplyDocCode(String applyDocCode) {
        this.applyDocCode = applyDocCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureEntity that = (PictureEntity) o;

        if (id != that.id) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (applyDocCode != null ? !applyDocCode.equals(that.applyDocCode) : that.applyDocCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (applyDocCode != null ? applyDocCode.hashCode() : 0);
        return result;
    }
}
