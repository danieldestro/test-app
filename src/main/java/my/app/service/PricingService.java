package my.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.app.entity.Country;
import my.app.entity.MonthBreakdown;
import my.app.entity.PriceSummary;
import my.app.entity.Proposal;
import my.app.entity.ServiceOffer;
import my.app.entity.ServicePriceBreakdown;
import my.app.entity.ServicePriceSummary;
import my.app.repository.CountryRepository;
import my.app.repository.MonthBreakdownRepository;
import my.app.repository.PriceSummaryRepository;
import my.app.repository.ProposalRepository;
import my.app.repository.ServiceRepository;

@Service
@Transactional
public class PricingService {

    private static final Logger      LOG = LoggerFactory.getLogger(PricingService.class);

    @Autowired
    private ProposalRepository       proposalRepository;

    @Autowired
    private ServiceRepository        serviceRepository;

    @Autowired
    private PriceSummaryRepository   priceSummaryRepository;

    @Autowired
    private CountryRepository        countryRepository;

    @Autowired
    private MonthBreakdownRepository monthBreakdownRepository;

    public void calculate(Integer id) {

        LOG.info("=== CALCULATE PROPOSAL PRICE - STARTED ===");

        Proposal proposal = proposalRepository.findOne(id);

        PriceSummary summary = new PriceSummary();
        summary.setProposal(proposal);

        List<ServiceOffer> services = serviceRepository.findByProposalId(id);
        services.forEach(service -> calculate(service, summary));

        LOG.info("=== CALCULATE PROPOSAL PRICE - FINISHED ===");

        LOG.info("=== List MonthBreakdowns for Unsaved PriceSummary  ===");
        printAllMonthBreakdowns(summary);

        LOG.info("=== SAVE PRICE SUMMARY - STARTED ===");

        PriceSummary savedSummary = priceSummaryRepository.saveAndFlush(summary);

        LOG.info("=== SAVE PRICE SUMMARY - FINISHED ===");

        LOG.info("=== List MonthBreakdowns for Saved PriceSummary  ===");
        printAllMonthBreakdowns(savedSummary);

        List<MonthBreakdown> allMonthBreakdowns = getAllMonthBreakdowns(summary, true);
        monthBreakdownRepository.bulkSave(allMonthBreakdowns);

        LOG.info("=== List MonthBreakdowns for Saved PriceSummary  ===");
        printAllMonthBreakdowns(savedSummary);
    }

    protected void calculate(ServiceOffer service, PriceSummary summary) {

        ServicePriceSummary sps = new ServicePriceSummary();
        sps.setPriceSummary(summary);

        List<Country> countries = countryRepository.findAll();

        BigDecimal summaryTotal = BigDecimal.ZERO;
        for (Country country : countries) {
            ServicePriceBreakdown spb = new ServicePriceBreakdown();
            spb.setServicePriceSummary(sps);
            spb.setCountry(country);

            BigDecimal monthBreakdownTotal = BigDecimal.ZERO;
            for (int i = 1; i <= service.getMonths(); i++) {
                MonthBreakdown mb = new MonthBreakdown();
                mb.setServicePriceBreakdown(spb);
                mb.setMonth(i);

                double baseCost = Math.random() * 1000;
                mb.setCost(new BigDecimal(i * baseCost).setScale(4, RoundingMode.HALF_UP));
                monthBreakdownTotal = monthBreakdownTotal.add(mb.getCost());

                spb.getMonthBreakdowns().add(mb);
            }
            summaryTotal = summaryTotal.add(monthBreakdownTotal);

            sps.getServicePriceBreakdowns().add(spb);
        }

        summary.getServicePriceSummaries().add(sps);
        summary.setPrice(summaryTotal);
    }

    public Proposal createProposal() {

        LOG.info("=== CREATING NEW PROPOSAL ===");

        Proposal p = new Proposal();
        p.setName("Test Proposal");

        ServiceOffer s1 = new ServiceOffer();
        s1.setName("S1");
        s1.setProposal(p);
        s1.setMonths(18);

        p.getServices().add(s1);

        ServiceOffer s2 = new ServiceOffer();
        s2.setName("S2");
        s2.setProposal(p);
        s2.setMonths(10);

        p.getServices().add(s2);

        proposalRepository.saveAndFlush(p);

        LOG.info("=== NEW PROPOSAL HAS BEEN CREATED ===");

        return p;
    }

    public void delete(int id) {

        LOG.info("=== DELETE EXISTING PROPOSAL - STARTED ===");

        Proposal proposal = proposalRepository.findOne(id);
        if (proposal == null) {
            LOG.error("Proposal does not exist: {}", id);
            throw new RuntimeException("Proposal does not exist");
        }

        PriceSummary priceSummary = priceSummaryRepository.findByProposal(proposal);
        if (priceSummary != null) {
            priceSummaryRepository.delete(priceSummary);
        }

        proposalRepository.delete(proposal);

        LOG.info("=== DELETE EXISTING PROPOSAL - FINISHED ===");
    }

    private void printAllMonthBreakdowns(PriceSummary summary) {
        List<MonthBreakdown> savedMonths = getAllMonthBreakdowns(summary);
        LOG.info("month breakdowns: {}", savedMonths.size());
        print(savedMonths);
    }

    private void print(List<MonthBreakdown> months) {
        for (MonthBreakdown month : months) {
            LOG.info(month.toString());
        }
    }

    private List<MonthBreakdown> getAllMonthBreakdowns(PriceSummary summary) {
        return getAllMonthBreakdowns(summary, false);
    }

    private List<MonthBreakdown> getAllMonthBreakdowns(PriceSummary summary, boolean newOnly) {
        List<MonthBreakdown> months = new ArrayList<>();
        for (ServicePriceSummary sps : summary.getServicePriceSummaries()) {
            for (ServicePriceBreakdown spb : sps.getServicePriceBreakdowns()) {
                List<MonthBreakdown> all = newOnly ? spb.getMonthBreakdowns().stream().filter(mb -> mb.getId() == null).collect(Collectors.toList())
                        : spb.getMonthBreakdowns();
                months.addAll(all);
            }
        }
        return months;
    }
}