package in.fabuleux.billStore1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import in.fabuleux.billStore1.services.BillService;

@RestController
public class BillController {

	@Autowired
	private BillService billService;
}
