package wg.wordgame.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wg.wordgame.model.Word;
import wg.wordgame.repository.WordRepository;

@Service
public class WordService implements WordServiceInterface {
	
	@Autowired
	WordRepository wordRepo;

	@Override
	public Word save(Word model) {
		return wordRepo.save(model);
	}

	@Override
	public List<Word> getAll() {
		return wordRepo.findAll();
	}
}