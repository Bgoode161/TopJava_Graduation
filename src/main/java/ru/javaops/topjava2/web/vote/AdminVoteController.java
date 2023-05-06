package ru.javaops.topjava2.web.vote;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.web.AuthUser;

import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController extends AbstractVoteController {

    static final String REST_URL = "/api/admin/votes";

    @Override
    @GetMapping("/winner")
    public List<Integer> getWinner() {
        return super.getWinner();
    }


}
