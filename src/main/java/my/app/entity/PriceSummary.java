package my.app.entity;

import java.math.BigDecimal;
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
@Table(name = "price_summary")
public class PriceSummary {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_price_summary_gen")
    @SequenceGenerator(sequenceName = "seq_price_summary", name = "seq_price_summary_gen", allocationSize = 1, initialValue = 1)
    private Integer                         id;

    @Column(name = "price")
    private BigDecimal                      price;

    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal                        proposal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceSummary", orphanRemoval = true)
    private final List<ServicePriceSummary> servicePriceSummaries = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ServicePriceSummary> getServicePriceSummaries() {
        return servicePriceSummaries;
    }
}