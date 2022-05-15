package wg.wordgame.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class ScoreService {
	
	 /**
     * Calculate the score for the entered word.
     * 
     * @param word
     * @return long
     * */
	public long getWordScore(String word) {
		long letterScore = this.getScoreByDistinctLetters(word);
		
		if (this.isPalindrome(word)) {
			letterScore += 3;
		} else if (isAlmostPalindrome(word)) {
			letterScore += 2;
		}
		
		return letterScore;
	}
	
	private long getScoreByDistinctLetters(String word) {
		return word.chars().distinct().count();
	}
	
	 /**
     * Test whether or not the word is palindrome.
     * 
     * @param word
     * @return boolean
     * */
	private boolean isPalindrome(String word) {
	    String clean = word.replaceAll("\\s+", "").toLowerCase();
	    int length = clean.length();
	    int forward = 0;
	    int backward = length - 1;
	    while (backward > forward) {
	        char forwardChar = clean.charAt(forward++);
	        char backwardChar = clean.charAt(backward--);
	        if (forwardChar != backwardChar)
	            return false;
	    }
	    return true;
	}

	 /**
     * Test whether or not the word is almost palindrome by removing one character.
     * 
     * @param word
     * @return boolean
     * */
	private static boolean isAlmostPalindrome(String word)
	{     
	    int n = word.length();
	 
	    Set<Character> st = new HashSet<Character>();
	     
	    for (int i = 0; i < n; i++) {
	        st.add(word.charAt(i));
	    }

	    boolean check = false;
	 
	    for (Character ele : st) {
	 
	        int low = 0, high = n - 1;
	        boolean flag = true;
	 
	        for (int i = 0; i < n; i++) {
	            if (word.charAt(low) == word.charAt(high)) {
	 
	                low++;
	                high--;
	            }
	 
	            else {
	                if (word.charAt(low) == ele) {
	 
	                    low++;
	                }
	                else if (word.charAt(high)== ele) {
	 
	                    high--;
	                }
	                else {
	                    flag = false;
	                    break;
	                }
	            }
	        }

	        if (flag == true) {
	            check = true;
	            break;
	        }
	    }
	 
	    if (check)
	        return true;
	    else
	        return false;
	}
}