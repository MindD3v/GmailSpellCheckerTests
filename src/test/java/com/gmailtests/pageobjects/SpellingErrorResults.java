package com.gmailtests.pageobjects;

import java.util.ArrayList;
import java.util.List;

public class SpellingErrorResults {
    private List<String> _spellingErrors;

    public SpellingErrorResults()
    {
        _spellingErrors = new ArrayList<String>();
    }
    public void add(String error)
    {
        _spellingErrors.add(error);
    }
    public int getTotalSpellingErrorCount()
    {
        return _spellingErrors.size();
    }
}
