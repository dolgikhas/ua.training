package ua.training.springtaxreports.controller;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.training.model.NoTaxReportException;
import ua.training.springtaxreports.entity.TaxReport;
import ua.training.springtaxreports.repository.TaxReportRepository;
import ua.training.springtaxreports.service.TaxReportService;
import ua.training.springtaxreports.service.UserService;

@Controller
public class StatisticReportController {
	@Autowired
	TaxReportService taxReportService;
    @Autowired
    UserService userService;
    @Value("${taxreport.role.admin}") String ROLE_ADMIN;
	@Value("${taxreport.role.user}") String ROLE_USER;
	@Value("${taxreport.statistic.list.taxreports}") String LIST_TAX_REPORTS;
	@Value("${taxreport.statistic.list.number.reports}") String NUMBER_REPORTS;
	@Value("${taxreport.statistic.report.id}") String REPORT_ID;
	@Value("${taxreport.command.correct}") String COMMAND_CORRECT;
	@Value("${taxreport.command.reject}") String COMMAND_REJECT;
	@Value("${taxreport.command.change_controller}") String COMMAND_CHANGE_CONTROLLER;
	@Value("${taxreport.statistic.command.name}") String COMMAND_NAME;
	@Value("${taxreport.statistic.status.on_review}") String STATUS_ON_REVIEW;
	// constants page address
	private final String COMMENT_REPORT_PAGE = "comment_report";
	private final String CORRECT_REPORT_PAGE = "correct_report";
	private final String STATISTIC_REPORTS_ADMIN_PAGE = "statistic_reports_admin";
	private final String STATISTIC_REPORTS_USER_PAGE = "statistic_reports_user";
	// constants attribute names
	private final String REPORT_ID_ATTRIBUTE = "reportId";
	
	private static final Logger logger = Logger.getLogger(
							StatisticReportController.class.getName() );
	
	@GetMapping( "/" + STATISTIC_REPORTS_ADMIN_PAGE )
	public String startStatisticReportsAdminPage( Model 	model )
	{
		List<TaxReport> listReports = taxReportService
							.getTaxReportsByContollerStatusOnReview(
									UserService.getCurrentUserId() );
		
		model.addAttribute( LIST_TAX_REPORTS, listReports );
		model.addAttribute( NUMBER_REPORTS, listReports.size() );

		return STATISTIC_REPORTS_ADMIN_PAGE;
	}
		
	@PostMapping( "/" + STATISTIC_REPORTS_ADMIN_PAGE )
	public String submitStatisticReportsAdminPage(
			@RequestParam(required = true, defaultValue = "" ) Integer reportId,
			@RequestParam(required = true, defaultValue = "" ) String command,
			 Model 	model )
	{
		// TODO: check parameters
		if ( command.equals( "" ) ){
			logger.log( Level.WARN, "WARNING in PostMapping method (statistic_reports). "
					+" redirect to /statistic_reports" );

			return "redirect:/" + STATISTIC_REPORTS_ADMIN_PAGE;
		}
		
		try {
			taxReportService.changeStatusToTaxReport( reportId, command );
		} catch ( NoTaxReportException exc ) {
			// TODO: send error message to view 
		}
		
		if ( command.equals( COMMAND_CORRECT ) || command.equals( COMMAND_REJECT ) ) {
			return "redirect:/" + COMMENT_REPORT_PAGE + "/" + reportId;
		}
				
		return "redirect:/" + STATISTIC_REPORTS_ADMIN_PAGE;
	}
	
	@GetMapping( "/" + STATISTIC_REPORTS_USER_PAGE )
	public String startStatisticReportsUserPage( Model 	model )
	{
		List<TaxReport> listReports = taxReportService.getReportByOwner(
					UserService.getCurrentUserId() );
		
		model.addAttribute( LIST_TAX_REPORTS, listReports );
		model.addAttribute( NUMBER_REPORTS, listReports.size() );

		return STATISTIC_REPORTS_USER_PAGE;
	}
		
	@PostMapping( "/" + STATISTIC_REPORTS_USER_PAGE )
	public String submitStatisticReportsUserPage(
			@RequestParam(required = true, defaultValue = "" ) Integer reportId,
			@RequestParam(required = true, defaultValue = "" ) String command,
			 Model 	model )
	{
		
		// TODO: check parameters
		if ( command.equals( "" ) ){
			logger.log( Level.WARN, "WARNING in PostMapping method (statistic_reports). "
					+" redirect to /statistic_reports" );

			return "redirect:/" + STATISTIC_REPORTS_USER_PAGE;
		}
		
		if ( command.equals( COMMAND_CORRECT )  ) {
			model.addAttribute( COMMAND_NAME, command );
			model.addAttribute( REPORT_ID, reportId );

			return "redirect:/" + CORRECT_REPORT_PAGE + "/" + reportId;
		}
		else if ( command.equals( COMMAND_CHANGE_CONTROLLER ) )
		{
			try {
				taxReportService.changeControllerForTaxReport( reportId );
			} catch ( NoTaxReportException exc ) {
				// TODO: send error message to view 
			}
		}
				
		return "redirect:/" + STATISTIC_REPORTS_USER_PAGE;
	}
	
	@GetMapping( "/" + COMMENT_REPORT_PAGE + "/{" + REPORT_ID_ATTRIBUTE  + "}" )
	public String startCommentReport(
			@PathVariable( REPORT_ID_ATTRIBUTE ) Integer reportId,
			 Model model )
	{		
		model.addAttribute( REPORT_ID_ATTRIBUTE, reportId );

		return COMMENT_REPORT_PAGE;		
	}
	
	@PostMapping( "/" + COMMENT_REPORT_PAGE + "/{" + REPORT_ID_ATTRIBUTE + "}" )
	public String addCommentReport( @PathVariable( REPORT_ID_ATTRIBUTE ) Integer reportId,
									@RequestParam(required = true, defaultValue = "" )
									 String comment,
									 Model model )
	{
		try {
			taxReportService.addCommentToTaxReport( reportId, comment );
		} catch ( NoTaxReportException exc ) {
			// TODO: send error message to view 
		}

		return "redirect:/" + STATISTIC_REPORTS_ADMIN_PAGE;		
	}
}