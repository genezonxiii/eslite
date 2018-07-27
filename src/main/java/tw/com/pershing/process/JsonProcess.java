package tw.com.pershing.process;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.com.pershing.domain.Cusls;
import tw.com.pershing.domain.CuslsDetail;
import tw.com.pershing.domain.Customer;
import tw.com.pershing.domain.Item;
import tw.com.pershing.error.CuslsAlreadyExistException;
import tw.com.pershing.error.CuslsDetailAlreadyExistException;
import tw.com.pershing.error.CustomerAlreadyExistException;
import tw.com.pershing.error.ItemAlreadyExistException;
import tw.com.pershing.service.CuslsService;
import tw.com.pershing.service.CustomerService;
import tw.com.pershing.service.ItemService;

@Component
public class JsonProcess {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerService customerService;
	@Autowired
	ItemService itemService;
	@Autowired
	CuslsService cuslsService;
	
	@SuppressWarnings("finally")
	public Boolean jsonToCustomer(File file){
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
        List<Customer> custList;
		try {
			custList = mapper.readValue(file, new TypeReference<List<Customer>>(){});
			for(Customer cust:custList ){
				logger.debug(cust.toString());
				customerService.addCustomer(cust);				
			}
			success = true;
		} catch (CustomerAlreadyExistException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (IOException e) {
			logger.error(e.getMessage());
			success = false;
		} finally {
			return success;
		}
	}
	
	@SuppressWarnings("finally")
	public Boolean jsonToItem(File file){
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
        List<Item> itemList;
		try {
			itemList = mapper.readValue(file, new TypeReference<List<Item>>(){});
			for(Item item:itemList ){
				logger.info(item.toString());
				itemService.addItem(item);				
			}
			success = true;
		} catch (ItemAlreadyExistException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (IOException e) {
			logger.error(e.getMessage());
			success = false;
		} finally {
			return success;
		}
	}

	@SuppressWarnings("finally")
	public Boolean jsonToCusls(File file){
		Boolean success = false;
		ObjectMapper mapper = new ObjectMapper();
        List<Cusls> cuslsList;
		try {
			cuslsList = mapper.readValue(file, new TypeReference<List<Cusls>>(){});
			for(Cusls cusls:cuslsList ){
				logger.info(cusls.toString());
				cuslsService.addCusls(cusls);
				cuslsService.deleteCuslsDetailBySlKey(cusls.getSlKey());
				for(CuslsDetail detail:cusls.getDetail()){
					detail.setSlKey(cusls.getSlKey());
					logger.info("Detail: {}", detail.toString());
					cuslsService.addCuslsDetail(detail);
				}
			}
			success = true;
		} catch (CuslsAlreadyExistException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (CuslsDetailAlreadyExistException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
			success = false;
		} catch (IOException e) {
			logger.error(e.getMessage());
			success = false;
		} finally {
			return success;
		}
	}
}
