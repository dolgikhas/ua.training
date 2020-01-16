package ua.training.springtaxreports.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.training.springtaxreports.entity.TaxReport;
import ua.training.springtaxreports.repository.TaxReportRepository;
import ua.training.springtaxreports.service.UserService;

@Controller
public class StatisticReportController {
	@Autowired
	TaxReportRepository taxReportRepository;
    @Autowired
    UserService userService;
    @Value("${taxreport.role.admin}")
     String ROLE_ADMIN;
	@Value("${taxreport.role.user}")
	 String ROLE_USER;
	@Value("${taxreport.statistic.list.taxreports}")
	 String LIST_TAX_REPORTS;
	@Value("${taxreport.statistic.list.number.reports}")
	 String NUMBER_REPORTS;
	@Value("${taxreport.statistic.report.id}")
	 String REPORT_ID;
	@Value("${taxreport.command.correct}")
	 String COMMAND_CORRECT;
	@Value("${taxreport.command.reject}")
	 String COMMAND_REJECT;
	@Value("${taxreport.command.change_controller}")
	 String COMMAND_CHANGE_CONTROLLER;
	@Value("${taxreport.statistic.command.name}")
	 String COMMAND_NAME;
	@Value("${taxreport.statistic.status.on_review}")
	 String STATUS_ON_REVIEW;
    
	@GetMapping( "/statistic_reports" )
	public String main( Model 	model )
	{
		String userId = UserService.getCurrentUserId();
		
		List<TaxReport> listReports;
		
		if ( UserService.userHasRole( ROLE_ADMIN ) ) {
			listReports = taxReportRepository
					.findTaxReportByControllerStatusOnReview( userId );
		} else if ( UserService.userHasRole( ROLE_USER ) ) {
			listReports = taxReportRepository.findTaxReportByOwner( userId );
		} else {
			throw new RuntimeException( "get not expected role!" );
		}
		
		model.addAttribute( LIST_TAX_REPORTS, listReports );
		model.addAttribute( NUMBER_REPORTS, listReports.size() );

		return "statistic_reports";
	}
		
	@PostMapping( "/statistic_reports" )
	public String submit(
			@RequestParam(required = true, defaultValue = "" ) Integer reportId,
			@RequestParam(required = true, defaultValue = "" ) String command,
			 Model 	model )
	{
		
		// TODO: check parameters
		if ( command.equals( "" ) ){
			return "redirect:/statistic_reports";
		}
		
    	Optional<TaxReport> optionalTaxReport = Optional.of(
    			taxReportRepository.findTaxReportById( reportId ) );
		if ( !optionalTaxReport.isPresent() ) {
			throw new RuntimeException( "TaxReport with id: "
					+ reportId + " not found" );
		}		
		TaxReport taxReport = optionalTaxReport.get();
	
		if ( UserService.userHasRole( ROLE_ADMIN ) )
		{
			taxReport.setStatus( command );			
			taxReportRepository.save( taxReport );
			
			if ( command.equals( COMMAND_CORRECT ) ||
				 command.equals( COMMAND_REJECT ) ) {				
				return "redirect:/comment_report/" + reportId;
			}
		}
		else if ( UserService.userHasRole( ROLE_USER ) )
		{
			if ( command.equals( COMMAND_CORRECT )  ) {
				model.addAttribute( COMMAND_NAME, command );
				model.addAttribute( REPORT_ID, reportId );

				return "redirect:/correct_report/" + reportId;
			}
			else if ( command.equals( COMMAND_CHANGE_CONTROLLER ) ) {
				taxReport.setController(
					userService.getController( taxReport.getController() ) );
				taxReport.setStatus( STATUS_ON_REVIEW );
				taxReportRepository.save( taxReport );
			}
		}
				
		return "redirect:/statistic_reports";
	}
	
	@GetMapping( "/comment_report/{reportId}" )
	public String startCommentReport(
			@PathVariable( "reportId" ) Integer reportId,
			 Model model )
	{		
		model.addAttribute( REPORT_ID, reportId );

		return "comment_report";		
	}
	
	@PostMapping( "/comment_report/{reportId}" )
	public String addCommentReport( @PathVariable("reportId") Integer reportId,
									@RequestParam(required = true, defaultValue = "" )
									 String comment,
									 Model model )
	{
    	Optional<TaxReport> optionalTaxReport = Optional.of(
    			taxReportRepository.findTaxReportById( reportId ) );
		if ( !optionalTaxReport.isPresent() ) {
			throw new RuntimeException( "TaxReport with id: "
					+ reportId + " not found" );
		}		
		TaxReport taxReport = optionalTaxReport.get();

		taxReport.setComment( comment );
		taxReportRepository.save( taxReport );

		return "redirect:/statistic_reports";		
	}
}