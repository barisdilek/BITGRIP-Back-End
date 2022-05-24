package de.bitgrip.codingchallenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "district")
@Getter
@Setter
public class District {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "area")
    private String area;
    @Column(name = "population")
    private String population;
    @Column(name = "foreignerPct")
    private String foreignerPct;
    @Column(name = "ageLt18Pct")
    private String ageLt18Pct;
    @Column(name = "ageGt75Pct")
    private String ageGt75Pct;
    @Column(name = "areaSettlePct")
    private String areaSettlePct;
    @Column(name = "areaNaturePct")
    private String areaNaturePct;
    @Column(name = "livingSpace")
    private String livingSpace;
    @Column(name = "cars")
    private String cars;
    @Column(name = "income")
    private String income;
    @Column(name = "bip")
    private String bip;
    @Column(name = "unemploymentRate")
    private String unemploymentRate;

    @Column(name = "lat")
    private String lat;
    @Column(name = "lot")
    private String lon;
    // Only in entity
    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private Set<Vote> votes;

    public District() {

    }

    public District(
            String lat,
            String lot,
            String category) {
        this.lat = lat;
        this.lon = lot;
        this.category = category;
    }

    public District(Integer id,
            String name,
            String area,
            String population,
            String foreignerPct,
            String ageLt18Pct,
            String ageGt75Pct,
            String areaSettlePct,
            String areaNaturePct,
            String livingSpace,
            String cars,
            String income,
            String bip,
            String unemploymentRate) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.population = population;
        this.foreignerPct = foreignerPct;
        this.ageLt18Pct = ageLt18Pct;
        this.ageGt75Pct = ageGt75Pct;
        this.areaSettlePct = areaSettlePct;
        this.areaNaturePct = areaNaturePct;
        this.livingSpace = livingSpace;
        this.cars = cars;
        this.income = income;
        this.bip = bip;
        this.unemploymentRate = unemploymentRate;
    }

    @Override
    public String toString() {
        String result = String.format(
                "\nDistrict[id=%d, name='%s']%n",
                this.id, this.name);
        if (this.votes != null) {
            for (Vote vote : votes) {
                result += String.format(
                        "Vote[id=%d, name='%s']%n",
                        vote.getId(), vote.getName());
            }
        }

        return result;
    }
}
