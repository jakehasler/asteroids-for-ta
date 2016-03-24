package edu.byu.cs.superasteroids.content;

/**
 * Handling the image objects
 */
public class Image {

    private int width;
    private int height;
    private String path;
    private int id;

    /**
     *
     * @param path Storing the string representation of the image path
     * @param width Image width
     * @param height Image height
     */
    public Image(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "width=" + width +
                ", height=" + height +
                ", path='" + path + '\'' +
                ", id=" + id +
                '}';
    }
}
