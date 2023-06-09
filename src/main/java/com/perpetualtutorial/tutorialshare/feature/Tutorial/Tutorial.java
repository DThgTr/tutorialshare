package com.perpetualtutorial.tutorialshare.feature.Tutorial;

import com.perpetualtutorial.tutorialshare.template.DataModelTemplate;
import com.perpetualtutorial.tutorialshare.template.EntityServices;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Entity
@Component
public class Tutorial implements DataModelTemplate {
    private @Id @GeneratedValue Long id;
    private String title;
    private String description;
    private String content;
    private Long userId;


    public Tutorial() {}
    public Tutorial(String title, String description, String content, Long userId) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.userId = userId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutorial tutorial = (Tutorial) o;
        return Objects.equals(id, tutorial.id) && Objects.equals(title, tutorial.title) && Objects.equals(description, tutorial.description) && Objects.equals(content, tutorial.content) && Objects.equals(userId, tutorial.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, content, userId);
    }

    @Override
    public String toString() {
        return "Tutorial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                '}';
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
