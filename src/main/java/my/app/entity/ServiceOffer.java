package my.app.entity;

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
@Table(name = "service")
public class ServiceOffer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICE_GEN")
    @SequenceGenerator(sequenceName = "SEQ_SERVICE", name = "SEQ_SERVICE_GEN", allocationSize = 1, initialValue = 1)
    private Integer  id;

    @Column(name = "name")
    private String   name;

    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    @Column(name = "months")
    private Integer  months;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }
}