package ua.training.springtaxreports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ua.training.springtaxreports.entity.TaxReport;

public interface TaxReportRepository extends CrudRepository<TaxReport, Long> {

    @Query(value = "select * from t_taxreport where owner = :owner", nativeQuery = true)
    List<TaxReport> findTaxReportByOwner( @Param("owner") String owner );
    
    @Query(value = "select * from t_taxreport where controller = :controller", nativeQuery = true)
    List<TaxReport> findTaxReportByController( @Param("controller") String controller );

    @Query(value = "select * from t_taxreport where "
    		+ "controller = :controller and status = 'on_review'",
    		nativeQuery = true)
    List<TaxReport> findTaxReportByControllerStatusOnReview( @Param("controller") String controller );
    
    TaxReport findTaxReportById( @Param( "id" ) Integer id );
}
