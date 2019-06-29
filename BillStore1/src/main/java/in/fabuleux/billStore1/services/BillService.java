package in.fabuleux.billStore1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.fabuleux.billStore1.entities.Branch;
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
	
	//Insert branch under company : id is company ID
	public ResponseEntity insertBranch(Long id,CompanyLoginInfo companyLoginInfo)
	{
		Company company = getCompanyById(id);
		Branch branch = new Branch(companyLoginInfo.getName(),companyLoginInfo.getAddress());
		branch.setCompany(company);
		Branch branch2 = branchRepository.save(branch);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyLoginInfo.getUserName());
		loginInfo.setPassword(companyLoginInfo.getPassword());
		loginInfo.setUserType("B");
		loginInfo.setTable_id(branch2.getId());
		loginInfoRepository.save(loginInfo);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	//Insert branch under branch : id is branch ID
	public ResponseEntity insertBranchUnderBranch(Long id,CompanyLoginInfo companyLoginInfo)
	{
		Branch branch = getBranchById(id);
		Branch branch1 = new Branch(companyLoginInfo.getName(),companyLoginInfo.getAddress());
		branch1.setParent(branch);
		branch1.setCompany(branch.getCompany());
		Branch branch2 = branchRepository.save(branch1);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyLoginInfo.getUserName());
		loginInfo.setPassword(companyLoginInfo.getPassword());
		loginInfo.setUserType("B");
		loginInfo.setTable_id(branch2.getId());
		loginInfoRepository.save(loginInfo);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	public Branch getBranchById(Long id)
	{
		Optional<Branch> branch = branchRepository.findById(id);
		return branch.get();
	}
	
	public Company getCompanyById(Long id)
	{
		Optional<Company> company = companyRepository.findById(id);
		return company.get();
	}
}
