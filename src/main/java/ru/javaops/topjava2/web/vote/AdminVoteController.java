package ru.javaops.topjava2.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.service.vote.VoteService;
import ru.javaops.topjava2.to.VoteTo;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController {

    @Autowired
    VoteService voteService;

    static final String REST_URL = "/api/admin/votes";

    @GetMapping
    public List<VoteTo> getAllByDate(@RequestParam("date_created") LocalDate dateCreated) {
        log.info("get all votes by date");
        return voteService.getAllByDate(dateCreated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vote by id");
        voteService.delete(id);
    }

}
