package my.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "srv_price_breakdown")
public class ServicePriceBreakdown {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_srv_price_bkd_gen")
    @SequenceGenerator(sequenceName = "seq_srv_price_bkd", name = "seq_srv_price_bkd_gen", allocationSize = 1, initialValue = 1)
    private Integer                    id;

    @ManyToOne
    @JoinColumn(name = "srv_price_summary_id")
    private ServicePriceSummary        servicePriceSummary;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country                    country;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "servicePriceBreakdown", orphanRemoval = true, targetEntity = MonthBreakdown.class)
    private final List<MonthBreakdown> monthBreakdowns = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServicePriceSummary getServicePriceSummary() {
        return servicePriceSummary;
    }

    public void setServicePriceSummary(ServicePriceSummary servicePriceSummary) {
        this.servicePriceSummary = servicePriceSummary;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<MonthBreakdown> getMonthBreakdowns() {
        return monthBreakdowns;
    }
}