package com.example.java_final;

public class Photo {
    private String label;
    private String imageUrl;

    public Photo() {}

    public Photo(String label, String imageUrl) {
        this.label = label;
        this.imageUrl = imageUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

