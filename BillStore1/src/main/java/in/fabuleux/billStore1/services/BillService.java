package in.fabuleux.billStore1.services;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.fabuleux.billStore1.entities.Branch;
import in.fabuleux.billStore1.entities.Company;
import in.fabuleux.billStore1.entities.LoginInfo;
import in.fabuleux.billStore1.entities.User;
import in.fabuleux.billStore1.models.CompanyLoginInfo;
import in.fabuleux.billStore1.repos.BranchRepository;
import in.fabuleux.billStore1.repos.CompanyRepository;
import in.fabuleux.billStore1.repos.LoginInfoRepository;
import in.fabuleux.billStore1.repos.UserRepository;
import in.fabuleux.billStore1.responses.BadRequestException;
import in.fabuleux.billStore1.responses.NotFoundException;
import in.fabuleux.billStore1.responses.UnAuthorizedException;

@Service
public class BillService {

	@Autowired 
	private PasswordEncoder passwordEncoder;
	
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
		if (findUsername(companyInfo.getUserName())) {
			throw new BadRequestException("Username already exists");
		}
		Company company = new Company(companyInfo.getName(),companyInfo.getAddress());
		Company company2 = companyRepository.save(company);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyInfo.getUserName());
		loginInfo.setPassword(passwordEncoder.encode(companyInfo.getPassword()));
		loginInfo.setUserType("C");
		loginInfo.setTable_id(company2.getId());
		loginInfoRepository.save(loginInfo);
		
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(company2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();
	}
	
	public ResponseEntity insertBranchUnderCompany(Long id,CompanyLoginInfo companyLoginInfo)
	{
		if (findUsername(companyLoginInfo.getUserName())) {
			throw new BadRequestException("Username already exists");
		}
		Company company = getCompanyById(id);
		Branch branch = new Branch(companyLoginInfo.getName(),companyLoginInfo.getAddress());
		branch.setCompany(company);
		Branch branch2 = branchRepository.save(branch);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyLoginInfo.getUserName());
		loginInfo.setPassword(passwordEncoder.encode(companyLoginInfo.getPassword()));
		loginInfo.setUserType("B");
		loginInfo.setTable_id(branch2.getId());
		loginInfoRepository.save(loginInfo);
		
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(branch2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();
	}
	
	//Insert branch under branch : id is branch ID
	public ResponseEntity insertBranchUnderBranch(Long id,CompanyLoginInfo companyLoginInfo)
	{
		if (findUsername(companyLoginInfo.getUserName())) {
			throw new BadRequestException("Username already exists");
		}
		Branch branch = getBranchById(id);
		Branch branch1 = new Branch(companyLoginInfo.getName(),companyLoginInfo.getAddress());
		branch1.setParent(branch);
		branch1.setCompany(branch.getCompany());
		Branch branch2 = branchRepository.save(branch1);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyLoginInfo.getUserName());
		loginInfo.setPassword(passwordEncoder.encode(companyLoginInfo.getPassword()));
		loginInfo.setUserType("B");
		loginInfo.setTable_id(branch2.getId());
		loginInfoRepository.save(loginInfo);
		
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(branch2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();
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
	
	
	public User getUserById(Long id)
	{
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	public ResponseEntity insertUserUnderCompany(Long id,CompanyLoginInfo companyLoginInfo)
	{
		if (findUsername(companyLoginInfo.getUserName())) {
			throw new BadRequestException("Username already exists");
		}
		Company company = getCompanyById(id);
		User user = new User(companyLoginInfo.getName(),companyLoginInfo.getAddress());
		user.setCompany(company);
		User user2 = userRepository.save(user);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyLoginInfo.getUserName());
		loginInfo.setPassword(passwordEncoder.encode(companyLoginInfo.getPassword()));
		loginInfo.setUserType("U");
		loginInfo.setTable_id(user2.getId());
		loginInfoRepository.save(loginInfo);
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();
	}
	
	public ResponseEntity insertUserUnderBranch(Long id,CompanyLoginInfo companyLoginInfo)
	{
		if (findUsername(companyLoginInfo.getUserName())) {
			throw new BadRequestException("Username already exists");
		}
		Branch branch = getBranchById(id);
		User user = new User(companyLoginInfo.getName(),companyLoginInfo.getAddress());
		user.setCompany(branch.getCompany());
		user.setBranch(branch);
		User user2 = userRepository.save(user);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(companyLoginInfo.getUserName());
		loginInfo.setPassword(passwordEncoder.encode(companyLoginInfo.getPassword()));
		loginInfo.setUserType("U");
		loginInfo.setTable_id(user2.getId());
		loginInfoRepository.save(loginInfo);
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();
	}
	
	public LoginInfo getLoginDetails(HashMap<String, String> hashMap)
	{
		String username = hashMap.get("username");
		String password = hashMap.get("password");
		Optional<LoginInfo> info = loginInfoRepository.findByUserName(username);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		if (passwordEncoder.matches(password, info.get().getPassword())) {
			return info.get();	
		}
		else {
			throw new UnAuthorizedException("Password doesn't match");
		}
	}
	
	public boolean findUsername(String username)
	{
		Optional<LoginInfo> info = loginInfoRepository.findByUserName(username);
		if (info.isPresent()) 
		{
			return true;
		}
		else {
			return false;
		}

	}
	
}
