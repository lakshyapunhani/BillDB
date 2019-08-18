package in.fabuleux.billStore2.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.fabuleux.billStore2.entities.Contact;
import in.fabuleux.billStore2.entities.Product;
import in.fabuleux.billStore2.entities.User;
import in.fabuleux.billStore2.services.BillService;

@RestController 
public class BillController 
{
	@Autowired
	BillService billService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/user")
	public ResponseEntity addUser(@RequestBody User user)
	{
		User user2 = new User();
		user2.setName(user.getName());
		user2.setUsername(user.getUsername());
		user2.setAddress(user.getAddress());
		user2.setPassword(passwordEncoder.encode(user.getPassword()));
		user2.setEmail(user.getEmail());
		return billService.addUser(user2);
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers()
	{
		return billService.getAllUsers();
	}

	@PostMapping("/login")
	public User getLogin(@RequestBody HashMap<String, String> hashMap)
	{
		return billService.getLoginDetails(hashMap);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity updateUserDetails(@PathVariable Long id,@RequestBody User user)
	{
		return billService.updateUser(id, user);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id)
	{
		return billService.deleteUser(id);
	}
	
	@PostMapping("/product/{id}")
	public ResponseEntity addProduct(@PathVariable Long id,@RequestBody Product product)
	{
		return billService.addProduct(id, product);
	}
	
	@GetMapping("/product/{id}")
	public List<Product> getProducts(@PathVariable Long id)
	{
		return billService.getProducts(id);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity updateProduct(@PathVariable Long id,@RequestBody Product product)
	{
		return billService.updateProduct(id, product);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity deleteProduct(@PathVariable Long id)
	{
		return billService.deleteProduct(id);
	}
	
	@PostMapping("/contact/{id}")
	public ResponseEntity addContact(@PathVariable Long id,@RequestBody Contact contact)
	{
		return billService.addContact(id, contact);
	}
	
	@GetMapping("/contact/{id}")
	public List<Contact> getContacts(@PathVariable Long id)
	{
		return billService.getContacts(id);
	}
	
	@GetMapping("/test")
	public String testMethod()
	{
		return "Hello world";
	}
	
	@PostMapping("invoice/{senderId}/{receiverId}")
	public ResponseEntity createInvoice(@PathVariable Long senderId,@PathVariable Long receiverId)
	{
		return billService.insertInvoice(senderId, receiverId);
	}
	
}