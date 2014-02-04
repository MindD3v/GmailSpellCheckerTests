package com.gmailtests.pageobjects;

import java.util.ArrayList;
import java.util.List;

public class SpellingErrorsList {
    private List<String> _spellingErrors;

    public SpellingErrorsList()
    {
        _spellingErrors = new ArrayList<String>();
    }
    public List<String> getSpellingErrors()
    {
        return _spellingErrors;
    }
    public void add(String error)
    {
        _spellingErrors.add(error);
    }
}
