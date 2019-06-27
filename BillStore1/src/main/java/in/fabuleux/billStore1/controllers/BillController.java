package in.fabuleux.billStore1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
}
