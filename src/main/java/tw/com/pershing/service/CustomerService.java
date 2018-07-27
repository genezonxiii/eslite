package tw.com.pershing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.error.CustomerAlreadyExistException;
import tw.com.pershing.repository.CustomerRepo;

@Service
@Configurable
public class CustomerService {

	@Autowired
	CustomerRepo repository;
	
	public boolean cNoExist(final String cNo) {
        return repository.findCustomerByCNo(cNo).size() > 0;
    }
	
	public Customer addCustomer(final Customer customer) {
//        if (cNoExist(customer.getcNo())) {
//            throw new CustomerAlreadyExistException("There is an Customer with that Customer No: " + customer.getcNo());
//        }

        final Customer returnCustomer = repository.saveUser(customer);
        return returnCustomer;
    }
}
