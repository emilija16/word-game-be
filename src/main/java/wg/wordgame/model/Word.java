package wg.wordgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import wg.wordgame.dto.WordDTO;

@Entity
@Getter
@Setter
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String word;
	
	private Long score;
	
	public Word(String word, Long score) {
		this.word = word;
		this.score = score;
	}
	
	public WordDTO asDTO() {
		return new WordDTO(word, score);
	}
	
	public Word() {}
}