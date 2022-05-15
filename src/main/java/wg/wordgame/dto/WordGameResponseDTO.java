package wg.wordgame.dto;

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
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WordDTO getWordDTO() {
		return wordDTO;
	}

	public void setWordDTO(WordDTO wordDTO) {
		this.wordDTO = wordDTO;
	}
}
