package ua.training.springtaxreports.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="t_taxreport")
public class TaxReport {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Size(min=2, message="Не меньше 5 знаков")
	private String pib;
	@Size(min=8, message="Не меньше 8 знаков")
	private String identnumber;
	@Size(min=3, message="Не меньше 3 знаков")
	private String taxdepartment;
	@Size(min=3, message="Не меньше 3 знаков")
	private String adress;
	@Size(min=1, message="Не меньше 1 знака")
	private String workers;
	@Size(min=1, message="Не меньше 1 знака")
	private String taxgroup;
	@Size(min=5, message="Не меньше 5 знаков")
	private String taxperiod;
	@Size(min=6, message="Не меньше 6 знаков")
	private String taxdate;
	@Size(min=5, message="Не меньше 5 знаков")
	private String taxcode;
	@Size(min=4, message="Не меньше 4 знаков")
	private String taxincome;
	private String owner;
	private String status;
	private String controller;
	private String comment;
	
	public TaxReport() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPib() {
		return pib;
	}
	public void setPib(String pib) {
		this.pib = pib;
	}
	public String getIdentnumber() {
		return identnumber;
	}
	public void setIdentnumber(String identnumber) {
		this.identnumber = identnumber;
	}
	public String getTaxdepartment() {
		return taxdepartment;
	}
	public void setTaxdepartment(String taxDepartment) {
		this.taxdepartment = taxDepartment;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getWorkers() {
		return workers;
	}
	public void setWorkers(String workers) {
		this.workers = workers;
	}
	public String getTaxgroup() {
		return taxgroup;
	}
	public void setTaxgroup(String taxgroup) {
		this.taxgroup = taxgroup;
	}
	public String getTaxperiod() {
		return taxperiod;
	}
	public void setTaxperiod(String taxperiod) {
		this.taxperiod = taxperiod;
	}
	public String getTaxdate() {
		return taxdate;
	}
	public void setTaxdate(String taxdate) {
		this.taxdate = taxdate;
	}
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public String getTaxincome() {
		return taxincome;
	}
	public void setTaxincome(String taxincome) {
		this.taxincome = taxincome;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}
