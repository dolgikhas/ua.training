package ua.training.springtaxreports.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.training.model.NoTaxReportException;
import ua.training.springtaxreports.entity.TaxReport;
import ua.training.springtaxreports.repository.TaxReportRepository;

/**
 * TaxReportService. Service class for work with db
 * @author next7a
 *
 */
@Service
public class TaxReportService {
	@Autowired
	TaxReportRepository taxReportRepository;
    @Autowired
    UserService userService;
	@Value("${taxreport.statistic.status.on_review}") String STATUS_ON_REVIEW;

    /**
     * Set parameters and save new tax report
     * @param taxReport
     * @return
     */
	public boolean saveNewTaxReport( @Valid TaxReport taxReport ) {
		taxReport.setController( userService.getController( "" ) );
		taxReport.setOwner( UserService.getCurrentUserId() );

		return setStatusAndSaveTaxReport( STATUS_ON_REVIEW, taxReport );
	}

	/**
	 * Get tax report by id
	 * @param reportId
	 * @return
	 * @throws NoTaxReportException 
	 */
	public TaxReport getTaxReportById(Integer reportId) throws NoTaxReportException {
		return taxReportRepository
				.findTaxReportById( reportId )
				.orElseThrow( () -> new NoTaxReportException( "id", reportId ) );
	}

	/**
	 * 
	 * @param status
	 * @param taxReport
	 * @return
	 */
	public boolean setStatusAndSaveTaxReport( String status,
			@Valid TaxReport taxReport ) {
		
		taxReport.setStatus( status );
		
		return saveTaxReport( taxReport );
	}

	/**
	 * Save tax report
	 * @param taxReport
	 * @return true in success
	 */
	private boolean saveTaxReport( TaxReport taxReport ) throws IllegalArgumentException {
		taxReportRepository.save( taxReport );
		
		return true;
	}
	
	/**
	 * Set parameters and save corrected tax report
	 * @param taxReport
	 */
	public boolean saveCorrectedTaxReport(@Valid TaxReport taxReport)
			throws IllegalArgumentException
	{
		taxReport.setComment( null );		
		
		return setStatusAndSaveTaxReport( STATUS_ON_REVIEW, taxReport );
	}

	public List<TaxReport> getTaxReportsByContollerStatusOnReview( String userId )
	{
		return taxReportRepository
				.findTaxReportByControllerStatusOnReview( userId );
	}

	public List<TaxReport> getReportByOwner( String userId ) {
//    	Optional<List<TaxReport>> optionalTaxReport = Optional.of(
//    			taxReportRepository.findTaxReportByOwner( userId ) );
//
//		if ( !optionalTaxReport.isPresent() ) {
//			throw new RuntimeException( "TaxReport by owner: " + userId
//					+ " not found!" );
//		}
//		
//		return optionalTaxReport.get();
		return taxReportRepository.findTaxReportByOwner( userId );
	}

	public boolean addCommentToTaxReport( Integer reportId, String comment )
			throws NoTaxReportException
	{
		TaxReport taxReport = getTaxReportById( reportId );
		
		taxReport.setComment( comment );
		
		return saveTaxReport( taxReport );		
	}

	public boolean changeControllerForTaxReport( Integer reportId )
			throws NoTaxReportException
	{
		TaxReport taxReport = getTaxReportById( reportId );

		taxReport.setController(
				userService.getController( taxReport.getController() ) );
		taxReport.setStatus( STATUS_ON_REVIEW );

		return saveTaxReport( taxReport );		
	}

	public boolean changeStatusToTaxReport( Integer reportId, String status )
				throws NoTaxReportException
	{
		TaxReport taxReport = getTaxReportById( reportId );
		
		taxReport.setStatus( status );
		
		return saveTaxReport( taxReport );		
	}

}
