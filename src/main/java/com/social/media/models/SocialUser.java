package com.social.media.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user_id", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private SocialProfile profile_id;

    @OneToMany(mappedBy = "social_user_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> user_groups = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


    public void setProfile_id(SocialProfile profile_id) {
        this.profile_id = profile_id;
        this.profile_id.setUser_id(this);
    }
}
