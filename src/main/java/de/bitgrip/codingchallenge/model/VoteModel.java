package de.bitgrip.codingchallenge.model;

import de.bitgrip.codingchallenge.entity.Vote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteModel {
    public VoteModel() {
    }

    public VoteModel(Vote vote) {

        if (vote.getId() != null) {
            this.id = vote.getId();
            this.partyType = vote.getPartyType();
            this.name = vote.getName();
            this.rank = vote.getRank();
            this.voteType = vote.getVoteType();
            this.count = vote.getCount();
            this.pct = vote.getPct();
            this.district = new DistrictListModel(vote.getDistrict());
        }
    }

    private Integer id;
    private String partyType;
    private String name;
    private String rank;
    private Short voteType;
    private String count;
    private String pct;

    private DistrictListModel district;
}
