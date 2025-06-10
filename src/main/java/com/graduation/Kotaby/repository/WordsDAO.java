package com.graduation.Kotaby.repository;

import java.util.List;

public interface WordsDAO {
    public List<String> getWordsByPage(int page);
}
