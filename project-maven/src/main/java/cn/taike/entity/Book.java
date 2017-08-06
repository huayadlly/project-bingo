package cn.taike.entity;

/**
 * Created by huayadlly on 2017/8/6.
 */
public class Book {

    private Integer id;
    private String bookName;
    private String projectName;
    private String type;
    private String cover;
    private String video;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Book() {
    }

    public Book(Integer id, String bookName, String projectName, String type, String cover, String video) {
        this.id = id;
        this.bookName = bookName;
        this.projectName = projectName;
        this.type = type;
        this.cover = cover;
        this.video = video;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", type='" + type + '\'' +
                ", cover='" + cover + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
