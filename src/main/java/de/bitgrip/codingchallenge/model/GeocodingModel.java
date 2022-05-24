package de.bitgrip.codingchallenge.model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeocodingModel {
    private Long place_id;
    private String licence;
    private String osm_type;
    private Long osm_id;
    ArrayList<String> boundingbox = new ArrayList<String>();
    private String lat;
    private String lon;
    private String display_name;
    private Long place_rank;
    private String category;
    private String type;
    private Double importance;
    private String icon;

    public GeocodingModel(String lat, String lon, String category) {
        this.lat = lat;
        this.lon = lon;
        this.category = category;
    }

}
