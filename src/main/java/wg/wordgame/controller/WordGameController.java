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
import wg.wordgame.dto.WordGameResponseDTO;
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
	public ResponseEntity<WordGameResponseDTO> create(@RequestBody WordDTO wordDTO) {
		eds.loadDictionaryFromJsonFile();
		
		ArrayList<Word> wordsList = (ArrayList<Word>) wordService.getAll();
		String word = wordDTO.getWord().toLowerCase();
		
		WordGameResponseDTO wordGameResponseDto = new WordGameResponseDTO();
		
		for(int i = 0; i < wordsList.size(); i++) {
			if(word.equals(wordsList.get(i).getWord())) {
				wordGameResponseDto.setMessage("Word already exists.");
				return new ResponseEntity<WordGameResponseDTO>(wordGameResponseDto, HttpStatus.BAD_REQUEST);
			}
		}
		
		if(eds.doesTheWordExists(word)) {
			long wordScore = this.scoreService.getWordScore(word);
			Word newWord = new Word(word, wordScore);
			Word model = wordService.save(newWord);
			wordGameResponseDto.setMessage("Word successfully added!");
			wordGameResponseDto.setWordDTO(model.asDTO());
			return new ResponseEntity<WordGameResponseDTO>(wordGameResponseDto, HttpStatus.CREATED);
//			return ResponseEntity.ok(model.asDTO());
		} else {
			wordGameResponseDto.setMessage("Entered word is not in English dictionary. Try again.");
			return new ResponseEntity<WordGameResponseDTO>(wordGameResponseDto, HttpStatus.BAD_REQUEST);
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