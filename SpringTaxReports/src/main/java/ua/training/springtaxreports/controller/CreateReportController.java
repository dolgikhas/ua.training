package ua.training.springtaxreports.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.training.springtaxreports.entity.Role;
import ua.training.springtaxreports.entity.TaxReport;
import ua.training.springtaxreports.entity.User;
import ua.training.springtaxreports.repository.TaxReportRepository;
import ua.training.springtaxreports.repository.UserRepository;
import ua.training.springtaxreports.service.UserService;

@Controller
 public class CreateReportController {
	@Autowired
	TaxReportRepository taxReportRepository;
    @Autowired
    UserRepository userRepository;
	
	@GetMapping( "/create_report" )
	public String main( Model model )
	{

        model.addAttribute("taxReportForm", new TaxReport() );

		return "create_report";		
	}
	
	@PostMapping( "/create_report" )
	public String add( @ModelAttribute("taxReportForm")
					   @Valid TaxReport taxReport,
						BindingResult 	bindingResult,
						Model 			model ) {

        if ( bindingResult.hasErrors() ) {
            return "create_report";
        }
        
		// TODO: check values, inputed by USER
		taxReport.setStatus( "on_review" );
		taxReport.setController( getController( "" ) );

		taxReportRepository.save( taxReport );

		return "redirect:/create_report";
	}

	@GetMapping( "/correct_report/{reportId}" )
	public String startCorrectReport( @PathVariable("reportId")
									   Integer reportId,
									   Model model )
	{
		TaxReport taxReport = taxReportRepository
								.findTaxReportById(reportId );
		model.addAttribute( "taxReportForm", taxReport );
		model.addAttribute( "command", "correct" );
		
		return "create_report";		
	}
	
	@PostMapping( "/correct_report/{reportId}" )
	public String correctReport( @PathVariable("reportId")
								  Integer reportId,
							    @ModelAttribute("taxReportForm")
							    @Valid TaxReport taxReport,
								 BindingResult 	bindingResult,
								 Model 			model ) {

		if ( bindingResult.hasErrors() ) {
			return "create_report";
		}

		// TODO: check values, inputed by USER

		taxReport.setStatus( "on_review" );
		taxReport.setComment( null );
		taxReportRepository.save( taxReport );
		
		return "redirect:/statistic_reports";
	}

	private String getController( String prevController ) {
		String		controller = null;
		List<User> listUsers = userRepository.findAll();
		for ( User user : listUsers ) {
			Set<Role> setRoles = user.getRoles();
			for ( Role role : setRoles )
				if ( role.getName().equals( "ROLE_ADMIN" ) &&
						!user.getUsername().equals(prevController) ) {
					controller = user.getUsername();
					break;
				}
		}
		return controller;
	}
}