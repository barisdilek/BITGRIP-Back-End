package de.bitgrip.codingchallenge.model;

import de.bitgrip.codingchallenge.entity.District;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictListModel {
    public DistrictListModel() {
    }

    public DistrictListModel(District district) {
        this.id = district.getId();
        this.name = district.getName();
        this.lat = district.getLat();
        this.lon = district.getLon();
    }

    private Integer id;
    private String name;
    private String lat;
    private String lon;
}
