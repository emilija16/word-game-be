package wg.wordgame.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Scope("singleton")
public class EnglishDictionaryService {
	
	public Map<String, Integer> dictionary;

	public void loadDictionaryFromJsonFile() {
		
		EnglishDictionaryService app = new EnglishDictionaryService();
	        String fileName = "data/words_dictionary.json";

	        InputStream is = app.getFileFromResourceAsStream(fileName);
	        
	        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            
            TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<HashMap<String, Integer>>() {};
            ObjectMapper mapper = new ObjectMapper();
            
			try {
				dictionary = mapper.readValue(reader, typeRef);
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }

	    private InputStream getFileFromResourceAsStream(String fileName) {
	        ClassLoader classLoader = getClass().getClassLoader();
	        InputStream inputStream = classLoader.getResourceAsStream(fileName);

	        if (inputStream == null) {
	            throw new IllegalArgumentException("file not found! " + fileName);
	        } else {
	            return inputStream;
	        }
	    }
	    
	    /**
	     * Test whether or not the word is present in the English dictionary.
	     * 
	     * @param word
	     * @return boolean
	     * */
	    public boolean doesTheWordExists(String word) {
	    	if (this.dictionary.get(word) != null && this.dictionary.get(word).equals(1)) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	}