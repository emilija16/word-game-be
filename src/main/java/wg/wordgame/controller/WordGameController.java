package wg.wordgame.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import wg.wordgame.dto.WordDTO;
import wg.wordgame.model.Word;
import wg.wordgame.service.EnglishDictionaryService;
import wg.wordgame.service.WordServiceInterface;
import wg.wordgame.service.ScoreService;

@Controller
@RequestMapping(value = "wordGame")
@CrossOrigin(origins = "http://localhost:3000")
public class WordGameController {
	
	@Autowired
	WordServiceInterface wordService;

	@Autowired
	EnglishDictionaryService eds;
	
	@Autowired
	ScoreService scoreService;

	@PostMapping("/add-word")
	public ResponseEntity<?> create(@RequestBody WordDTO wordDTO) {
		eds.loadDictionaryFromJsonFile();
		
		ArrayList<Word> wordsList = (ArrayList<Word>) wordService.getAll();
		String word = wordDTO.getWord().toLowerCase();
		
		for(int i = 0; i < wordsList.size(); i++) {
			if(word.equals(wordsList.get(i).getWord())) {
				return new ResponseEntity<>("Entered word already exists. Try again.", HttpStatus.BAD_REQUEST);
			}
		}
		
		if(eds.doesTheWordExists(word)) {
			long wordScore = this.scoreService.getWordScore(word);
			Word model = wordService.save(new Word(word, wordScore));
			return ResponseEntity.ok(model.asDTO());
		} else {
			return new ResponseEntity<>("Entered word is not in English dictionary. Try again.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<WordDTO>> getAll() {
		List<Word> listOfWords = wordService.getAll();
		List<WordDTO> wordDTOs = new ArrayList<>();
		listOfWords.stream().forEach(w -> wordDTOs.add(w.asDTO()));
		return ResponseEntity.ok(wordDTOs);
	}
}