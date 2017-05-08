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
@SequenceGenerator(sequenceName = "seq_srv_price_bkd", name = "seq_srv_price_bkd", allocationSize = 1, initialValue = 1)
public class ServicePriceBreakdown {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_srv_price_bkd")
    private Integer                    id;

    @ManyToOne
    @JoinColumn(name = "srv_price_summary_id")
    private ServicePriceSummary        servicePriceSummary;

    @Column(name = "country_code")
    private String                     countryCode;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "servicePriceBreakdown", orphanRemoval = true)
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<MonthBreakdown> getMonthBreakdowns() {
        return monthBreakdowns;
    }

}