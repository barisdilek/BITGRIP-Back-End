package de.bitgrip.codingchallenge;

import de.bitgrip.codingchallenge.entity.District;
import de.bitgrip.codingchallenge.infrastructure.ImportCSV;
import de.bitgrip.codingchallenge.repository.DistrictRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class VotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotesApplication.class, args);
        //System.out.println("version: " + SpringVersion.getVersion());
    }

    private static final Logger logger = LoggerFactory.getLogger(VotesApplication.class);
    @Autowired
    ImportCSV importCSV;

    @Autowired
    DistrictRepository districtRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        System.out.println("The server is running on http:localhost:8090");

        // Import
        List<District> districts = importCSV.getDistrict();
        districtRepository.saveAll(districts);

    }

}
