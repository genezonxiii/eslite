package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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
	
	public CuslsDetail findCuslsDetailBySlKeyAndPNo(CuslsDetail cuslsDetail) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<CuslsDetail> query = em
				.createQuery("select c from CuslsDetail c where c.slKey=:slKey and c.pNo=:pNo", CuslsDetail.class)
				.setParameter("slKey", cuslsDetail.getSlKey())
				.setParameter("pNo", cuslsDetail.getpNo());
		List<CuslsDetail> cuslsDetailList = query.getResultList();
		logger.info("findCuslsBySlKey length: {}", cuslsDetailList.size());
		em.close();
		if (cuslsDetailList.isEmpty()) {
			return null;
		} else if (cuslsDetailList.size() > 0) {
			return cuslsDetailList.get(0);
		}
		return null;
	}
	
	public CuslsDetail saveCuslsDetail(CuslsDetail cuslsDetail) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		CuslsDetail existsCuslsDetail = cuslsDetail;
		if (cuslsDetail.getCuslsDetailId() != null) {
			existsCuslsDetail = em.find(CuslsDetail.class, cuslsDetail.getCuslsDetailId());
		}
		et.begin();
		if (!em.contains(existsCuslsDetail)) {
			em.persist(cuslsDetail);
		} else {
			em.merge(cuslsDetail);
		}
		et.commit();
		em.close();
		return cuslsDetail;
	}
	
	public Integer deleteCuslsDetailBySlKey(String slKey) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Query query = em
			.createQuery("delete from CuslsDetail c where c.slKey=:slKey")
			.setParameter("slKey", slKey);
		et.begin();
		Integer count = query.executeUpdate();
		logger.info("deleteCuslsDetailBySlKey count: {}", count);
		et.commit();
		em.close();
		return count;
	}
}
