package de.bitgrip.codingchallenge.service;

import de.bitgrip.codingchallenge.entity.Vote;
import de.bitgrip.codingchallenge.model.VoteModel;
import de.bitgrip.codingchallenge.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepository;

    public List<String> getAll() {
        List<String> voteList = voteRepository
                .findAll()
                .stream()
                .map(x -> x.getName())
                .distinct()
                .collect(Collectors.toList());
        return voteList;
    }

    public VoteModel getByName(String voteName) {
        return new VoteModel(voteRepository.findAll().stream()
                .filter(x -> Objects.equals(x.getName(), voteName))
                .findFirst()
                .orElse(new Vote()));
    }
}
