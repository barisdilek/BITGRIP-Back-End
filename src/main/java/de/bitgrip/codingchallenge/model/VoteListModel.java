package de.bitgrip.codingchallenge.model;

import de.bitgrip.codingchallenge.entity.Vote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteListModel {
    public VoteListModel(Vote vote) {

        this.name = vote.getName();
        this.voteType = vote.getVoteType();
        this.pct = vote.getPct();
    }

    private String name;
    private Short voteType;
    private String pct;
}
