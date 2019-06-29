package in.fabuleux.billStore1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.fabuleux.billStore1.models.CompanyLoginInfo;
import in.fabuleux.billStore1.services.BillService;

@RestController
public class BillController {

	@Autowired
	private BillService billService;
	
	@PostMapping("/company")
	public ResponseEntity insertCompany(@RequestBody CompanyLoginInfo companyLoginInfo)
	{
		return billService.insertCompany(companyLoginInfo);
	}
	
	@PostMapping("/company/{id}")
	public ResponseEntity insertBranchUnderCompany(@PathVariable Long id,@RequestBody CompanyLoginInfo companyLoginInfo)
	{
		return billService.insertBranchUnderCompany(id, companyLoginInfo);
	}
	
	@PostMapping("/company/{companyId}/branch/{id}")
	public ResponseEntity insertBranchUnderBranch(@PathVariable Long id,@RequestBody CompanyLoginInfo companyLoginInfo)
	{
		return billService.insertBranchUnderBranch(id, companyLoginInfo);
	}
	
	@PostMapping("/user/{id}")
	public ResponseEntity insertUserUnderCompany(@PathVariable Long id,@RequestBody CompanyLoginInfo companyLoginInfo)
	{
		return billService.insertUserUnderCompany(id, companyLoginInfo);
	}
	
	@PostMapping("/user/{companyId}/branch/{id}")
	public ResponseEntity insertUserUnderBranch(@PathVariable Long id,@RequestBody CompanyLoginInfo companyLoginInfo)
	{
		return billService.insertUserUnderBranch(id, companyLoginInfo);
	}
	
}
