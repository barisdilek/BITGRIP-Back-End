package de.bitgrip.codingchallenge.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "vote")
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "partyType")
    private String partyType;
    @Column(name = "name")
    private String name;
    @Column(name = "rank")
    private String rank;
    @Column(name = "voteType")
    private Short voteType;
    @Column(name = "count")
    private String count;
    @Column(name = "pct")
    private String pct;

    // districtId is not exists in entity
    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;

    public Vote() {
    }

    public Vote(String partyType,
            String name,
            String rank,
            short voteType,
            String count,
            String pct,
            District district) {
        this.partyType = partyType;
        this.name = name;
        this.rank = rank;
        this.voteType = voteType;
        this.count = count;
        this.pct = pct;
        this.district = district;
    }
}
