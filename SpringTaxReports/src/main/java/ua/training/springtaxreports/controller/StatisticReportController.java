package ua.training.springtaxreports.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import ua.training.springtaxreports.entity.Role;
import ua.training.springtaxreports.entity.TaxReport;
import ua.training.springtaxreports.entity.User;
import ua.training.springtaxreports.repository.TaxReportRepository;
import ua.training.springtaxreports.repository.UserRepository;

@Controller
public class StatisticReportController {
	@Autowired
	TaxReportRepository taxReportRepository;
    @Autowired
    UserRepository userRepository;

	@GetMapping( "/statistic_reports" )
	public String main( Model 	model ) {
		
		String userId = getCurrentUserId();
		
		List<TaxReport> listReports;
		
		if ( hasRole( "ROLE_ADMIN" ) ) {
			listReports = taxReportRepository
					.findTaxReportByControllerStatusOnReview( userId );
		} else if ( hasRole( "ROLE_USER" ) ) {
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
						   String reportId,
						  @RequestParam(required = true, defaultValue = "" )
							String command,
							Model 	model ) {
		
		// TODO: check parameters
		if ( command.equals( "" ) )
		{
			main( model );
			return "statistic_reports";
		}
		
		TaxReport taxReport = taxReportRepository
					.findTaxReportById( Integer.parseInt( reportId )  );
		
		// check: is user has role 'admin' - set selected status 
		// TODO: replace "constant" to variable
		if ( hasRole( "ROLE_ADMIN" ) )
		{
			taxReport.setStatus( command );			
			taxReportRepository.save( taxReport );
			
			if ( command.equals( "correct" ) || command.equals( "reject" ) ) {				
				return "redirect:/comment_report/" + reportId;
			}
		}
		// TODO: replace "constant" to variable
		else if ( hasRole( "ROLE_USER" ) )
		{
			if ( command.equals( "correct" )  ) {
				// TODO: replace "constants" to variable
				model.addAttribute( "command", command );
				model.addAttribute( "reportId", reportId );

				return "redirect:/correct_report/" + reportId;
			}
			// TODO: replace "constant" to variable
			else if ( command.equals( "change_controller" )) {

				taxReport.setController(
						getController( taxReport.getController() ) );
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
		TaxReport taxReport = taxReportRepository.findTaxReportById( reportId );
		taxReport.setComment( comment );
		taxReportRepository.save( taxReport );

		return "redirect:/statistic_reports";		
	}


	// TODO: replace to user service
	String getCurrentUserId() {
		User user = (User) SecurityContextHolder.getContext()
												.getAuthentication()
												.getPrincipal();
	    return user.getUsername();		
	}
	
	
	// TODO: replace to user service
	private String getController( String prevController ) {
		String		controller = null;
		List<User> listUsers = userRepository.findAll();
		for ( User user : listUsers ) {
			Set<Role> setRoles = user.getRoles();
			for ( Role role : setRoles )
				if ( role.getName().equals( "ROLE_ADMIN" ) &&
						!user.getUsername().equals(prevController) ) {
					controller = user.getUsername();
					return controller;
				}

		}
		return controller;
	}
	
	protected boolean hasRole( String role )
	{
		for ( GrantedAuthority authority : SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities()) {
	        if ( role.equals( authority.getAuthority() ) )
	        	return true;
		}

	    return false;
	}
}