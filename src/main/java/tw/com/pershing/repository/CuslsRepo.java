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

import tw.com.pershing.domain.Cusls;

@Repository
public class CuslsRepo {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public List<Cusls> findCuslsBySlKey(String slKey) {
		TypedQuery<Cusls> query = emf.createEntityManager()
				.createQuery("select c from Cusls c where c.slKey=:slKey", Cusls.class)
				.setParameter("slKey", slKey);
		List<Cusls> cuslsList = query.getResultList();
		logger.info("findCuslsBySlKey length: {}", cuslsList.size());
		return cuslsList;
	}
	
	public Cusls saveCusls(Cusls cusls) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Cusls existsCusls = em.find(Cusls.class, cusls.getSlKey());
		if (!em.contains(existsCusls)) {
			em.persist(cusls);
		} else {
			em.merge(cusls);
		}
		et.commit();
		em.close();
		return cusls;
	}
}
