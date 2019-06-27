package in.fabuleux.billStore1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.fabuleux.billStore1.entities.Company;
import in.fabuleux.billStore1.entities.LoginInfo;
import in.fabuleux.billStore1.models.CompanyLoginInfo;
import in.fabuleux.billStore1.repos.BranchRepository;
import in.fabuleux.billStore1.repos.CompanyRepository;
import in.fabuleux.billStore1.repos.LoginInfoRepository;
import in.fabuleux.billStore1.repos.UserRepository;

@Service
public class BillService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginInfoRepository loginInfoRepository;
	
	public ResponseEntity insertCompany(CompanyLoginInfo companyInfo)
	{
		Company company = new Company(companyInfo.getName(),companyInfo.getAddress());
		Company company2 = companyRepository.save(company);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyInfo.getUserName());
		loginInfo.setPassword(companyInfo.getPassword());
		loginInfo.setUserType("C");
		loginInfo.setTable_id(company2.getId());
		loginInfoRepository.save(loginInfo);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
