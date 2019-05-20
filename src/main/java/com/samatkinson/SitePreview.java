package com.samatkinson;

public class SitePreview {
    public final String image;
    public final String desc;
    public final String title;

    public SitePreview(String image, String desc, String title) {
        this.image = image;
        this.desc = desc;
        this.title = title;
    }

    @Override
    public String toString() {
        return "SitePreview{" +
                "image='" + image + '\'' +
                ", desc='" + desc + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
