package com.example.engjp_11;

public class SentenceItem {
    private String sentence;
    private String meaning;
    private boolean isSaved;

    // Bắt buộc có constructor rỗng để Firebase sử dụng
    public SentenceItem() {}

    public SentenceItem(String sentence, String meaning, boolean isSaved) {
        this.sentence =sentence;
        this.meaning = meaning;
        this.isSaved = isSaved;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String word) {
        this.sentence = word;
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

