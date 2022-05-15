package wg.wordgame.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordDTO {

	private Long id;
	private String word;
	private Long score;
	
	public WordDTO(String word, Long score) {
		super();
		this.word = word;
		this.score = score;
	}
}
