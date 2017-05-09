package my.app.repository;

import java.util.List;

import my.app.entity.MonthBreakdown;

public interface MonthBreakdownRepository {

    void bulkSave(List<MonthBreakdown> monthBreakdowns);
}