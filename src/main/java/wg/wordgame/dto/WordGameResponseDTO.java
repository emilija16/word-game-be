package wg.wordgame.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordGameResponseDTO {

	String message;
	WordDTO wordDTO;
	
	public WordGameResponseDTO() {
		super();
	}
	
	public WordGameResponseDTO(String message, WordDTO wordDTO) {
		super();
		this.message = message;
		this.wordDTO = wordDTO;
	}
}