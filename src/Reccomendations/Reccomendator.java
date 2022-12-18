package Reccomendations;

import Exceptions.LanguageLevelNotFoundException;
import Exceptions.LearnedWordNotFoundException;
import LanguageLevels.LanguageLevelDao;
import LearnedWords.LearnedWordsDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Reccomendator {
    private final LanguageLevelDao languageLevelDao;
    private final LearnedWordsDao learnedWordsDao = new LearnedWordsDao();

    public Reccomendator() throws IOException, SQLException {
        languageLevelDao = new LanguageLevelDao();
        try {
            languageLevelDao.fillTables();
        } catch (LanguageLevelNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addLearnedWord(String word) throws SQLException {
        learnedWordsDao.addLearnedWord(learnedWordsDao.getNumWords()+1, word);
    }

    public Set<String> getRecommendations(String level,int num) throws SQLException, LanguageLevelNotFoundException, IOException, LearnedWordNotFoundException {
        Set<String> recommendations = new HashSet<>();
        int wordsToGenerate=0;
        try {
            learnedWordsDao.fillTable(getWords(level));
        } catch (
                LearnedWordNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0;i<num/3;i++) {
            String tmp =learnedWordsDao.getWord();
            if(learnedWordsDao.getNumWords()<num/3)
            {
                wordsToGenerate=num/3-learnedWordsDao.getNumWords();
            }
            while(recommendations.contains(tmp))
            {
                tmp = learnedWordsDao.getWord();
            }
            recommendations.add(tmp);
        }
        for(int i = 0;i<num/3+wordsToGenerate/2+wordsToGenerate%2;i++) {
            String tmp = languageLevelDao.getWord(level);
            while(recommendations.contains(tmp))
            {
                tmp = languageLevelDao.getWord(level);
            }
            recommendations.add(tmp);
        }
        for(int i = 0;i<num/3+num%3+wordsToGenerate/2;i++) {
            String tmp =languageLevelDao.getWord(languageLevelDao.getHigherLevel(level));
            while(recommendations.contains(tmp))
            {
                tmp = languageLevelDao.getWord(languageLevelDao.getHigherLevel(level));
            }
            recommendations.add(tmp);
        }
        return recommendations;
    }

    public Set<String> getWords(String level) throws SQLException, LanguageLevelNotFoundException {
        return languageLevelDao.getWords(level);
    }
}
