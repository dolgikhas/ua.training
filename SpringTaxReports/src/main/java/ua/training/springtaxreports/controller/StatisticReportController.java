package ua.training.springtaxreports.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import ua.training.springtaxreports.entity.TaxReport;
import ua.training.springtaxreports.repository.TaxReportRepository;
import ua.training.springtaxreports.service.UserService;

@Controller
public class StatisticReportController {
	@Autowired
	TaxReportRepository taxReportRepository;
    @Autowired
    UserService userService;

	@GetMapping( "/statistic_reports" )
	public String main( Model 	model ) {
		
		String userId = UserService.getCurrentUserId();
		
		List<TaxReport> listReports;
		
		if ( UserService.userHasRole( "ROLE_ADMIN" ) ) {
			listReports = taxReportRepository
					.findTaxReportByControllerStatusOnReview( userId );
		} else if ( UserService.userHasRole( "ROLE_USER" ) ) {
			listReports = taxReportRepository.findTaxReportByOwner( userId );
		} else {
			throw new RuntimeException( "get not expected role!" );
		}
		
		model.addAttribute( "allTaxReports", listReports );
		model.addAttribute( "number_reports", listReports.size() );

		return "statistic_reports";
	}
		
	@PostMapping( "/statistic_reports" )
	public String submit( @RequestParam(required = true, defaultValue = "" )
						    Integer reportId,
						  @RequestParam(required = true, defaultValue = "" )
							String command,
							Model 	model ) {
		
		// TODO: check parameters
		if ( command.equals( "" ) )
		{
			main( model );
			return "statistic_reports";
		}
		
    	Optional<TaxReport> optionalTaxReport = Optional.of(
    			taxReportRepository.findTaxReportById( reportId ) );
		if ( !optionalTaxReport.isPresent() ) {
			throw new RuntimeException( "TaxReport with id: "
					+ reportId + " not found" );
		}		
		TaxReport taxReport = optionalTaxReport.get();
	
		// TODO: replace "constant" to variable
		if ( UserService.userHasRole( "ROLE_ADMIN" ) )
		{
			taxReport.setStatus( command );			
			taxReportRepository.save( taxReport );
			
			if ( command.equals( "correct" ) || command.equals( "reject" ) ) {				
				return "redirect:/comment_report/" + reportId;
			}
		}
		// TODO: replace "constant" to variable
		else if ( UserService.userHasRole( "ROLE_USER" ) )
		{
			if ( command.equals( "correct" )  ) {
				// TODO: replace "constants" to variable
				model.addAttribute( "command", command );
				model.addAttribute( "reportId", reportId );

				return "redirect:/correct_report/" + reportId;
			}
			// TODO: replace "constant" to variable
			else if ( command.equals( "change_controller" )) {

				taxReport.setController( userService.getController(
						taxReport.getController() ) );
				taxReport.setStatus( "on_review" );
				taxReportRepository.save( taxReport );
			}
		}
		
		main( model );
		
		return "statistic_reports";		
	}
	
	@GetMapping( "/comment_report/{reportId}" )
	public String startCommentReport( @PathVariable("reportId") Integer reportId,
									   Model model )
	{		
		model.addAttribute( "reportId", reportId );

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