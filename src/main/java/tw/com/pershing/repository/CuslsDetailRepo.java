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

import tw.com.pershing.domain.CuslsDetail;

@Repository
public class CuslsDetailRepo {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public List<CuslsDetail> findCuslsDetailBySlKeyAndPNo(CuslsDetail cuslsDetail) {
		TypedQuery<CuslsDetail> query = emf.createEntityManager()
				.createQuery("select c from CuslsDetail c where c.slKey=:slKey and c.pNo=:pNo", CuslsDetail.class)
				.setParameter("slKey", cuslsDetail.getSlKey())
				.setParameter("pNo", cuslsDetail.getpNo());
		List<CuslsDetail> cuslsDetailList = query.getResultList();
		logger.info("findCuslsBySlKey length: {}", cuslsDetailList.size());
		return cuslsDetailList;
	}
	
	public CuslsDetail saveCuslsDetail(CuslsDetail cuslsDetail) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		if (!em.contains(cuslsDetail)) {
			em.persist(cuslsDetail);
		} else {
			em.merge(cuslsDetail);
		}
		et.commit();
		em.close();
		return cuslsDetail;
	}
}
