package com.social.media.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private SocialUser user_id;

    private String description;

    public void setUser_id(SocialUser user_id) {
        this.user_id = user_id;
        if(user_id.getProfile_id() != this){
            user_id.setProfile_id(this);
        }
    }
}
