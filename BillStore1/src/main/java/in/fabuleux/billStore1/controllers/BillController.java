package in.fabuleux.billStore1.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.fabuleux.billStore1.entities.Branch;
import in.fabuleux.billStore1.entities.Company;
import in.fabuleux.billStore1.entities.LoginInfo;
import in.fabuleux.billStore1.entities.User;
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
	
	@PostMapping("/login")
	public LoginInfo getLogin(@RequestBody HashMap<String, String> hashMap)
	{
		return billService.getLoginDetails(hashMap);
	}
	
	@GetMapping("/company/{id}")
	public Company getCompany(@PathVariable Long id)
	{
		return billService.getCompanyById(id);
	}
	
	@GetMapping("/branch/{id}")
	public Branch getBranch(@PathVariable Long id)
	{
		return billService.getBranchById(id);
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id)
	{
		return billService.getUserById(id);
	}
	
	@GetMapping("/test")
	public String testMethod()
	{
		return "Hello";
	}
	
}
