package com.example.engjp_11;

public class WordItem {
    private String word;
    private String meaning;
    private boolean isSaved;

    // Bắt buộc có constructor rỗng để Firebase sử dụng
    public WordItem() {}

    public WordItem(String word, String meaning, boolean isSaved) {
        this.word = word;
        this.meaning = meaning;
        this.isSaved = isSaved;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}

