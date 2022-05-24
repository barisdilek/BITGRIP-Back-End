package de.bitgrip.codingchallenge.model;

import de.bitgrip.codingchallenge.entity.District;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DistrictModel {
    public DistrictModel(District district) {
        this.id = district.getId();
        this.name = district.getName();

        this.area = district.getArea();
        this.population = district.getPopulation();
        this.foreignerPct = district.getForeignerPct();
        this.ageLt18Pct = district.getAgeLt18Pct();
        this.ageGt75Pct = district.getAgeGt75Pct();
        this.areaSettlePct = district.getAreaSettlePct();
        this.areaNaturePct = district.getAreaNaturePct();
        this.livingSpace = district.getLivingSpace();
        this.cars = district.getCars();
        this.income = district.getIncome();
        this.bip = district.getBip();
        this.unemploymentRate = district.getUnemploymentRate();

        this.lat = district.getLat();
        this.lon = district.getLon();

        List<VoteListModel> votes = district.getVotes()
                .stream()
                .map(x -> new VoteListModel(x))
                .collect(Collectors.toList());

        // Compare by first name and then last name
        Comparator<VoteListModel> compareByName = Comparator
                .comparing(VoteListModel::getName)
                .thenComparingInt(VoteListModel::getVoteType)
                .thenComparing(VoteListModel::getPct);

        List<VoteListModel> sortedVotes = votes.stream()
                .sorted(compareByName)
                .collect(Collectors.toList());

        this.votes = sortedVotes;
    }

    private Integer id;
    private String name;
    private String area;
    private String population;
    private String foreignerPct;
    private String ageLt18Pct;
    private String ageGt75Pct;
    private String areaSettlePct;
    private String areaNaturePct;
    private String livingSpace;
    private String cars;
    private String income;
    private String bip;
    private String unemploymentRate;
    private String lat;
    private String lon;
    private List<VoteListModel> votes;
}
