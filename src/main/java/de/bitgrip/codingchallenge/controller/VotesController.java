package de.bitgrip.codingchallenge.controller;

import de.bitgrip.codingchallenge.model.VoteModel;
import de.bitgrip.codingchallenge.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VotesController {
    @Autowired
    VoteService voteService;

    @GetMapping(path = { "", "/" })
    public ResponseEntity<?> get(@RequestParam(name = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            VoteModel vote = voteService.getByName(name);
            return new ResponseEntity<VoteModel>(vote, HttpStatus.OK);
        } else {
            List<String> allDistrict = voteService.getAll();
            return new ResponseEntity<List<String>>(allDistrict, HttpStatus.OK);
        }

    }
}