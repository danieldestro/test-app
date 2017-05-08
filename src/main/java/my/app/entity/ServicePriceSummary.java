package my.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "srv_price_summary")
public class ServicePriceSummary {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_srv_price_summary_gen")
    @SequenceGenerator(sequenceName = "seq_srv_price_summary", name = "seq_srv_price_summary_gen", allocationSize = 1, initialValue = 1)
    private Integer                           id;

    @ManyToOne
    @JoinColumn(name = "price_summary_id")
    private PriceSummary                      priceSummary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicePriceSummary", orphanRemoval = true)
    private final List<ServicePriceBreakdown> servicePriceBreakdowns = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PriceSummary getPriceSummary() {
        return priceSummary;
    }

    public void setPriceSummary(PriceSummary priceSummary) {
        this.priceSummary = priceSummary;
    }

    public List<ServicePriceBreakdown> getServicePriceBreakdowns() {
        return servicePriceBreakdowns;
    }
}