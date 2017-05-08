package my.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.app.entity.MonthBreakdown;
import my.app.entity.PriceSummary;
import my.app.entity.Proposal;
import my.app.entity.ServiceOffer;
import my.app.entity.ServicePriceBreakdown;
import my.app.entity.ServicePriceSummary;
import my.app.repository.PriceSummaryRepository;
import my.app.repository.ProposalRepository;
import my.app.repository.ServiceRepository;

@Service
@Transactional
public class PricingService {

    private static final Logger    LOG       = LoggerFactory.getLogger(PricingService.class);

    private static final String[]  countries = { "BR", "GB", "US" };

    @Autowired
    private ProposalRepository     proposalRepository;

    @Autowired
    private ServiceRepository      serviceRepository;

    @Autowired
    private PriceSummaryRepository priceSummaryRepository;

    public void calculate(Integer id) {

        LOG.info("=== CALCULATE PROPOSAL PRICE - STARTED ===");

        Proposal proposal = proposalRepository.findOne(id);

        PriceSummary summary = new PriceSummary();
        summary.setProposal(proposal);

        List<ServiceOffer> services = serviceRepository.findByProposalId(id);
        services.forEach(service -> calculate(service, summary));

        LOG.info("=== CALCULATE PROPOSAL PRICE - FINISHED ===");

        PriceSummary savedSummary = priceSummaryRepository.saveAndFlush(summary);

        List<MonthBreakdown> months = getMonths(summary);
        LOG.info("months: {}", months.size());
        print(months);

        List<MonthBreakdown> savedMonths = getMonths(savedSummary);
        LOG.info("saved months: {}", savedMonths.size());
        print(savedMonths);
    }

    private void print(List<MonthBreakdown> months) {
        LOG.info("=== LIST MonthBreakdowns ===");
        for (MonthBreakdown month : months) {
            LOG.info(month.toString());
        }
    }

    private List<MonthBreakdown> getMonths(PriceSummary summary) {
        List<MonthBreakdown> months = new ArrayList<>();
        for (ServicePriceSummary sps : summary.getServicePriceSummaries()) {
            for (ServicePriceBreakdown spb : sps.getServicePriceBreakdowns()) {
                months.addAll(spb.getMonthBreakdowns());
            }
        }
        return months;
    }

    public void calculate(ServiceOffer service, PriceSummary summary) {

        ServicePriceSummary sps = new ServicePriceSummary();
        sps.setPriceSummary(summary);

        BigDecimal summaryTotal = BigDecimal.ZERO;
        for (String country : countries) {
            ServicePriceBreakdown spb = new ServicePriceBreakdown();
            spb.setServicePriceSummary(sps);
            spb.setCountryCode(country);

            BigDecimal monthBreakdownTotal = BigDecimal.ZERO;
            for (int i = 1; i <= 12; i++) {
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

        p.getServices().add(s1);

        ServiceOffer s2 = new ServiceOffer();
        s2.setName("S2");
        s2.setProposal(p);

        p.getServices().add(s2);

        proposalRepository.saveAndFlush(p);

        LOG.info("=== NEW PROPOSAL HAS BEEN CREATED ===");

        return p;
    }
}