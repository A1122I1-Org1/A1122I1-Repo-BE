package com.example.be.entity;

import javax.persistence.*;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(columnDefinition = "TEXT")
    private String introduce;
    @Column(columnDefinition = "TEXT")
    private String image;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    public Topic() {
    }

    public Topic(Integer id, String name, String introduce, String image, String content, Boolean deleteFlag, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.image = image;
        this.content = content;
        this.deleteFlag = deleteFlag;
        this.faculty = faculty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
