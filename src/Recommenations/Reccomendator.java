package Recommenations;

import Exceptions.LanguageLevelNotFoundException;
import Exceptions.LearnedWordNotFoundException;
import LanguageLevels.LanguageLevelDao;
import LearnedWords.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Reccomendator {
    private final LanguageLevelDao languageLevelDao;
    LearnedWordsDao learnedWordsDao = new LearnedWordsDao();

    public Reccomendator() throws IOException, SQLException {
        languageLevelDao = new LanguageLevelDao();
        try {
            languageLevelDao.fillTables();
        } catch (LanguageLevelNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Set<String> getRecommendations(String level,int num) throws SQLException, LanguageLevelNotFoundException, IOException, LearnedWordNotFoundException {
        Set<String> recommendations = new HashSet<>();
        try {
            learnedWordsDao.fillTable(getWords(level));
        } catch (
                LearnedWordNotFoundException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0;i<num/3;i++)
        {
            String tmp = languageLevelDao.getWord(level);
            while(recommendations.contains(tmp))
            {
                tmp = languageLevelDao.getWord(level);
            }
            recommendations.add(tmp);
        }
        for(int i = 0;i<num/3+num%3;i++)
        {
            String tmp =languageLevelDao.getWord(languageLevelDao.getHigherLevel(level));
            while(recommendations.contains(tmp))
            {
                tmp = languageLevelDao.getWord(languageLevelDao.getHigherLevel(level));
            }
            recommendations.add(tmp);
        }
        for(int i = 0;i<num/3;i++)
        {
            String tmp =learnedWordsDao.getWord();
            while(recommendations.contains(tmp))
            {
                tmp = learnedWordsDao.getWord();
            }
            recommendations.add(tmp);
            System.out.println(tmp);
        }

        return recommendations;
    }

    public Set<String> getWords(String level) throws SQLException, LanguageLevelNotFoundException {
        return languageLevelDao.getWords(level);
    }
}
