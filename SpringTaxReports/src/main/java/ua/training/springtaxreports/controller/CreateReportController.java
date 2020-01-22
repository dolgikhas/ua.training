package ua.training.springtaxreports.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.training.model.NoTaxReportException;
import ua.training.springtaxreports.entity.Role;
import ua.training.springtaxreports.entity.TaxReport;
import ua.training.springtaxreports.entity.User;
import ua.training.springtaxreports.repository.TaxReportRepository;
import ua.training.springtaxreports.repository.UserRepository;
import ua.training.springtaxreports.service.TaxReportService;
import ua.training.springtaxreports.service.UserService;

@Controller
 public class CreateReportController {
	@Autowired
	TaxReportService taxReportService;
    @Autowired
    UserService userService;
    
    // constants page address
    private final String CREATE_REPORT_PAGE 	= "create_report";
    private final String CORRECT_REPORT_PAGE 	= "correct_report";
    private final String STATISTIC_REPORTS_PAGE = "statistic_reports";
    // constants attributes
    private final String TAX_REPORT_FORM 		= "taxReportForm";
    private final String REPORT_ID_ATTRIBUTE 	= "reportId";
    private final String CORRECT_COMMAND 		= "correct";
	
	@GetMapping( "/" + CREATE_REPORT_PAGE )
	public String main( Model model )
	{
        model.addAttribute( TAX_REPORT_FORM, new TaxReport() );

		return CREATE_REPORT_PAGE;		
	}
	
	@PostMapping( "/" + CREATE_REPORT_PAGE )
	public String add( @ModelAttribute( TAX_REPORT_FORM )
					   @Valid TaxReport taxReport,
						BindingResult 	bindingResult,
						Model 			model ) {

        if ( bindingResult.hasErrors() ) {
            return CREATE_REPORT_PAGE;
        }
        
		// TODO: check values, inputed by USER
        taxReportService.saveNewTaxReport( taxReport );

		return "redirect:/" + CREATE_REPORT_PAGE;
	}

	@GetMapping( "/" + CORRECT_REPORT_PAGE + "/{" + REPORT_ID_ATTRIBUTE + "}" )
	public String startCorrectReport( @PathVariable( REPORT_ID_ATTRIBUTE )
									   Integer reportId,
									   Model model )
	{
		TaxReport taxReport = null;
		
		try {
			taxReport = taxReportService.getTaxReportById( reportId );
		} catch ( NoTaxReportException exc ) {
			// TODO: output message by view, redirect to error page
		}

		model.addAttribute( TAX_REPORT_FORM, taxReport );
		model.addAttribute( "command", CORRECT_COMMAND );
		
		return CREATE_REPORT_PAGE;		
	}
	
	@PostMapping( "/" + CORRECT_REPORT_PAGE + "/{" + REPORT_ID_ATTRIBUTE + "}" )
	public String correctReport( @PathVariable( REPORT_ID_ATTRIBUTE )
								  Integer reportId,
							    @ModelAttribute( TAX_REPORT_FORM )
							    @Valid TaxReport taxReport,
								 BindingResult 	bindingResult,
								 Model 			model ) {

		if ( bindingResult.hasErrors() ) {
			return "redirect:/" + CORRECT_REPORT_PAGE + "/" + reportId;
		}

		// TODO: check values, inputed by USER
		taxReportService.saveCorrectedTaxReport( taxReport );
		
		return "redirect:/" + STATISTIC_REPORTS_PAGE;
	}
}