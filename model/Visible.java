package edu.byu.cs.superasteroids.model;

import edu.byu.cs.superasteroids.content.Image;

/**
 * For any object that can be seen
 */
public class Visible {

    protected Image image;
    protected long id;

    public Visible(Image image) {
        this.image = image;
    }

    public Visible() {}

    public Image getRealImage() {
        return image;
    }

    public void setRealImage(Image image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Visible{" +
                "image=" + image +
                ", id=" + id +
                '}';
    }
}
