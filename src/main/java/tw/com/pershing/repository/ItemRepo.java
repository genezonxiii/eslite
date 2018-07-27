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

import tw.com.pershing.domain.Item;

@Repository
public class ItemRepo {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public List<Item> findItemByMatnr(String cNo) {
		TypedQuery<Item> query = emf.createEntityManager()
				.createQuery("select i from Item i where i.matnr=:matnr", Item.class)
				.setParameter("matnr", cNo);
		List<Item> itemList = query.getResultList();
		logger.info("findItemByMatnr length: {}", itemList.size());
		return itemList;
	}
	
	public Item saveItem(Item item) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Item existsItem = em.find(Item.class, item.getMatnr());
		if (!em.contains(existsItem)) {
			em.persist(item);
		} else {
			em.merge(item);
		}
		et.commit();
		em.clear();
		em.close();
		return item;
	}
}
