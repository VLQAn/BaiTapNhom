package com.example.engjp_11;

public class ClassCourseItem {
    private String imageResId;
    private String title;
    private String description;

    // Constructor rỗng bắt buộc cho Firebase
    public ClassCourseItem() {}

    // Constructor có tham số (tuỳ chọn)
    public ClassCourseItem(String imageResId, String title, String description) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
    }

    // Getter và Setter
    public String getImageResId() {
        return imageResId;
    }

    public void setImageResId(String imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
