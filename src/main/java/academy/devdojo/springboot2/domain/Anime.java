package academy.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private LocalDateTime created;
    private LocalDateTime updated;

    private Anime(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.created = builder.created;
        this.updated = builder.updated;
        this.username = builder.username;
    }


    public static class Builder{

        private Long id;
        private String name;
        private String username;
        private LocalDateTime created;
        private LocalDateTime updated;


        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder created(LocalDateTime created){
            this.created = created;
            return this;
        }

        public Builder updated(LocalDateTime updated){
            this.updated = updated;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Anime build(){
            return new Anime(this);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
