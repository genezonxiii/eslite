package tw.com.pershing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Item;
import tw.com.pershing.error.ItemAlreadyExistException;
import tw.com.pershing.repository.ItemRepo;

@Service
@Configurable
public class ItemService {

	@Autowired
	ItemRepo repository;
	
	public boolean matnrExist(final String matnr) {
        return repository.findItemByMatnr(matnr).size() > 0;
    }
	
	public Item addItem(final Item item) {
//        if (matnrExist(item.getMatnr())) {
//            throw new ItemAlreadyExistException("There is an Item with that Material No: " + item.getMatnr());
//        }

        final Item returnItem = repository.saveItem(item);
        return returnItem;
    }
}
