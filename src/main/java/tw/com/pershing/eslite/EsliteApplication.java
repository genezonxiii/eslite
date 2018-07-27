package tw.com.pershing.eslite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import tw.com.pershing.process.JsonProcess;

@SpringBootApplication(scanBasePackages={
	"tw.com.pershing.process",
	"tw.com.pershing.service",
	"tw.com.pershing.repository"
})
@EntityScan("tw.com.pershing.domain")
public class EsliteApplication {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String ROOT_PATH = "/home/mysqlmove/eslite/";
	private static final String DATA_DIR = "in/";
	private static final String BACKUP_DIR = "backup/";
	private static final String[] SUB_DIR = {"customer/", "item/", "cusls/"};
	private String processDatetime;
	
	@Autowired
	JsonProcess pro;

	public static void main(String[] args) {
		SpringApplication.run(EsliteApplication.class, args);
	}
	
	@Bean
	public String runMyFiles() {
		Boolean processFlag = false;
		Integer index = 0;

		File dir = new File(ROOT_PATH + DATA_DIR);
		File[] fileList = dir.listFiles();
		this.setProcessDatetime( genDateFormat("yyyyMMddHHmmss") );
		
		for(File file: fileList) {
			if (file.isDirectory()) { continue; }
			String filename = file.getName();
			logger.info("Process File: {}", filename);
			if (filename.startsWith("Item")){
				processFlag = pro.jsonToItem(file);
				index = 1;
			} else if (filename.startsWith("Customer")){
				processFlag = pro.jsonToCustomer(file);
				index = 0;
			} else if (filename.startsWith("Cusls")){
				processFlag = pro.jsonToCusls(file);
				index = 2;
			}
			if (processFlag) {
				logger.info("MOVE TO DIR: {}", ROOT_PATH + BACKUP_DIR + SUB_DIR[index]);
				moveFile(file, ROOT_PATH + BACKUP_DIR + SUB_DIR[index]); 
			}
		}

		return null;
	}
	
	private static String genDateFormat(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	private void moveFile(File file, String PathTo){
		if (! file.renameTo(new File(PathTo + file.getName() + "." + this.getProcessDatetime())) ) {
			logger.error("RENAME FAIL: " + PathTo + file.getName());
		}
	}

	public String getProcessDatetime() {
		return processDatetime;
	}

	public void setProcessDatetime(String processDatetime) {
		this.processDatetime = processDatetime;
	}
}
