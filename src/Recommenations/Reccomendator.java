package Recommenations;

import Exceptions.LanguageLevelNotFoundException;
import LanguageLevels.LanguageLevelDao;
import Checkers.Checkers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reccomendator {
    private final LanguageLevelDao languageLevelDao;
    public Reccomendator() throws IOException, SQLException {
        languageLevelDao = new LanguageLevelDao();
        try {
            languageLevelDao.filltables();
        } catch (LanguageLevelNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getReccomendations(String level) throws SQLException, LanguageLevelNotFoundException {
        Set<String> recommendations = new HashSet<>();
        for(int i = 0;i<3;i++)
        {
            recommendations.add(languageLevelDao.getWord(level));
        }
        for(int i = 0;i<3;i++)
        {
            recommendations.add(languageLevelDao.getWord(languageLevelDao.getHigherLevel(level)));
        }
        return recommendations;
    }

}
