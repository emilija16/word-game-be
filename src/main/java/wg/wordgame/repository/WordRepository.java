package wg.wordgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wg.wordgame.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {}
