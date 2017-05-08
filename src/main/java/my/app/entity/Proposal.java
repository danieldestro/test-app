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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "proposal")
@SequenceGenerator(sequenceName = "SEQ_PROPOSAL", name = "SEQ_PROPOSAL", allocationSize = 1, initialValue = 1)
public class Proposal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROPOSAL")
    private Integer            id;

    @Column(name = "name")
    private String             name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "proposal", orphanRemoval = true)
    private List<ServiceOffer> services = new ArrayList<>();

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

    public void setServices(List<ServiceOffer> services) {
        this.services = services;
    }

    public List<ServiceOffer> getServices() {
        return services;
    }
}