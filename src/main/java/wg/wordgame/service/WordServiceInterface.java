package wg.wordgame.service;
import java.util.List;

import wg.wordgame.model.Word;

public interface WordServiceInterface {
	Word save(Word model);
	List<Word> getAll();
}
