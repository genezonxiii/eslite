package tw.com.pershing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Cusls;
import tw.com.pershing.domain.CuslsDetail;
import tw.com.pershing.error.CuslsDetailAlreadyExistException;
import tw.com.pershing.repository.CuslsDetailRepo;
import tw.com.pershing.repository.CuslsRepo;

@Service
@Configurable
public class CuslsService {

	@Autowired
	CuslsRepo repository;
	@Autowired
	CuslsDetailRepo detailRepository;
	
	public boolean slKeyExist(final String slKey) {
        return repository.findCuslsBySlKey(slKey).size() > 0;
    }
	
	public boolean slKeyAndPNoExist(final CuslsDetail cuslsDetail) {
        return detailRepository.findCuslsDetailBySlKeyAndPNo(cuslsDetail) != null;
    }
	
	public CuslsDetail findCuslsDetailBySlKeyAndPNo(final CuslsDetail cuslsDetail) {
        return detailRepository.findCuslsDetailBySlKeyAndPNo(cuslsDetail);
    }
	
	public Cusls addCusls(final Cusls cusls) {
//        if (slKeyExist(cusls.getSlKey())) {
//            throw new CuslsAlreadyExistException("There is an Customer Sale List with that SLKEY: " + cusls.getSlKey());
//        }

        final Cusls returnCusls = repository.saveCusls(cusls);
        return returnCusls;
    }
	
	public CuslsDetail addCuslsDetail(final CuslsDetail detail) {
//		if (slKeyAndPNoExist(detail)) {
//            throw new CuslsDetailAlreadyExistException("There is an Customer Sale Detail with that SLKEY and PNO: " + 
//            		detail.getSlKey() + ", " + detail.getpNo());
//        }
		
		final CuslsDetail returnDetail = detailRepository.saveCuslsDetail(detail);
		return returnDetail;
	}
	
	public Integer deleteCuslsDetailBySlKey(final String slKey) {
		return detailRepository.deleteCuslsDetailBySlKey(slKey);
	}
}
