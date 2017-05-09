package my.app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "month_breakdown")
public class MonthBreakdown {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MTH_BKD_GEN")
    @SequenceGenerator(sequenceName = "SEQ_MTH_BKD", name = "SEQ_MTH_BKD_GEN", allocationSize = 1, initialValue = 1)
    private Integer               id;

    @ManyToOne
    @JoinColumn(name = "srv_price_breakdown_id")
    private ServicePriceBreakdown servicePriceBreakdown;

    @Column(name = "month_number")
    private Integer               month;

    @Column(name = "cost")
    private BigDecimal            cost;

    @Override
    public String toString() {
        return "[" + id + "] month: " + month + ", cost: " + cost + ", spb.id: " + servicePriceBreakdown.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServicePriceBreakdown getServicePriceBreakdown() {
        return servicePriceBreakdown;
    }

    public void setServicePriceBreakdown(ServicePriceBreakdown servicePriceBreakdown) {
        this.servicePriceBreakdown = servicePriceBreakdown;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

}