package ru.javaops.topjava2.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    List<Vote> findAllByDateCreated(LocalDate dateCreated, Sort sort);

    Optional<Vote> findVoteByDateCreatedAndAndUserId(LocalDate localDate, int userId);

    List<Vote> findByUserId(int id, Sort sort);

}
