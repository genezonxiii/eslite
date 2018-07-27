package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Customer;

@Repository
public class CustomerRepo {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public List<Customer> findCustomerByCNo(String cNo) {
		TypedQuery<Customer> query = emf.createEntityManager()
				.createQuery("select c from Customer c where c.cNo=:cNo", Customer.class)
				.setParameter("cNo", cNo);
		List<Customer> userList = query.getResultList();
		logger.info("findUserByCNo length: {}", userList.size());
		return userList;
	}
	
	public Customer saveUser(Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Customer existsCustomer = em.find(Customer.class, customer.getcNo());
		if (!em.contains(existsCustomer)) {
			em.persist(customer);
		} else {
			em.merge(customer);
		}
		et.commit();
		em.close();
		return customer;
	}
}
