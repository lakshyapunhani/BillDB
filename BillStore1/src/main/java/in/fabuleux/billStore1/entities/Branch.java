package in.fabuleux.billStore1.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Branch 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
//	private Long parent_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
    private Branch parent;

	@OneToMany(mappedBy = "parent")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Branch> subBranches;
	
	@OneToMany(mappedBy = "branch")
	private List<User> users;

	public Branch() {
		super();
	}

	public Branch(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		//this.parent_id = parent_id;
	}
	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public Branch getParent() {
		return parent;
	}

	public void setParent(Branch parent) {
		this.parent = parent;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

//	public Long getParent_id() {
//		return parent_id;
//	}
//
//	public void setParent_id(Long parent_id) {
//		this.parent_id = parent_id;
//	}
	
	
}
