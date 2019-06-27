package in.fabuleux.billStore1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
