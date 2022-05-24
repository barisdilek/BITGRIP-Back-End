package de.bitgrip.codingchallenge.scheduler;

import de.bitgrip.codingchallenge.entity.District;
import de.bitgrip.codingchallenge.model.GeocodingModel;
import de.bitgrip.codingchallenge.repository.DistrictRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Geocoding {

    @Autowired
    DistrictRepository districtRepository;
    private static final Logger logger = LoggerFactory.getLogger(Geocoding.class);

    private List<GeocodingModel> fetchData(District district) {
        RestTemplate restTemplate = new RestTemplate();

        String sourceUrl = "https://nominatim.openstreetmap.org/search?q=%7B" +
                HtmlUtils.htmlEscape(district.getName()).replace(" ", "&nbsp;") +
                "%7D&format=jsonv2&countrycodes=de&limit=1";

        GeocodingModel[] geocodingModels;
        try {
            ResponseEntity<GeocodingModel[]> res = restTemplate.getForEntity(sourceUrl,
                    GeocodingModel[].class);

            if (Objects.equals(res.getStatusCode(), HttpStatus.OK)) {
                geocodingModels = res.getBody();
                return Arrays.stream(geocodingModels)
                        .map(x -> new GeocodingModel(x.getLat(), x.getLon(), x.getCategory()))
                        .collect(Collectors.toList());
            } else {
                logger.info(
                        "\nCron geocoding scheduler job \n[district.id=" + district.getId() + "]"
                                + "\n[StatusCode : " + res.getStatusCode() + "] : "
                                + "\n[sourceUrl=" + sourceUrl + "]");
            }
        } catch (Exception e) {
            logger.info(
                    "\nCron geocoding scheduler job \n[district.id=" + district.getId()
                            + "]\n[eror : " + e.getMessage() + "] : "
                            + "\n[sourceUrl=" + sourceUrl + "]");
            district.setCategory("error");
        }
        return null;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void setLatandLot() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);

        List<GeocodingModel> geocodingModels;

        District district = districtRepository.findAll().stream()
                .filter(x -> x.getCategory() != "boundary" && x.getCategory() != "error")
                .findFirst().orElse(new District());
        if (district.getId() != null) {
            geocodingModels = fetchData(district);

            if (geocodingModels != null && geocodingModels.size() > 0) {
                GeocodingModel geo = geocodingModels.get(0);
                String category = geo.getCategory();
                String lat = geo.getLat();
                String lon = geo.getLon();

                if (category != null && !category.isEmpty() && category == "boundary") {

                    district.setLat(lat);
                    district.setLon(lon);
                    district.setCategory(category);

                    logger.info(
                            "\nCron geocoding scheduler job \n[district.id=" + district.getId() + "] : "
                                    + strDate);
                } else {
                    logger.info(
                            "\nCron geocoding scheduler job "
                                    + "\n[district.id=" + district.getId() + "]"
                                    + "\n[Category : " + category + "] : "
                                    + "\n[lat : " + lat + "] : "
                                    + "\n[lon : " + lon + "] : "
                                    + strDate);
                    if (category != null && !category.isEmpty() && category != "boundary") {
                        district.setCategory("error");
                    }
                }
            } else {
                logger.info(
                        "\nCron geocoding scheduler job \n[district.id=" + district.getId() + "]"
                                + "\n[body=" + geocodingModels + "]"
                                + strDate);
                district.setCategory("error");
            }

            District savedDistrict = districtRepository.save(district);
        } else {
            logger.info(
                    "\nCron geocoding scheduler job [district not found ] : "
                            + strDate);
        }
    }
}
