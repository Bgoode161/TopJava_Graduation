package ru.javaops.topjava2.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    List<Vote> findAllByDateCreated(LocalDate dateCreated, Sort sort);

    Optional<Vote> findVoteByDateCreatedAndAndUserId(LocalDate localDate, int userId);

    List<Vote> findByUserId(int id, Sort sort);

    @Query(value = "SELECT restaurant_id FROM vote WHERE date=:localDate  group by vote.restaurant_id having count(*) = (SELECT max(count) FROM (SELECT restaurant_id, count(*) as count FROM vote GROUP BY vote.restaurant_id) as votecount)", nativeQuery = true)
    List<Integer> getMostRatedRestaurantsIdsByDate(@Param("localDate") LocalDate localDate);

}
