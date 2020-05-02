package ua.lviv.iot.database.lab4.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoutersEntityPK implements Serializable {
    private String ip;
    private Integer officeId;

    @Column(name = "ip")
    @Id
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "office_id")
    @Id
    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutersEntityPK that = (RoutersEntityPK) o;
        return Objects.equals(ip, that.ip) &&
                Objects.equals(officeId, that.officeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, officeId);
    }
}
