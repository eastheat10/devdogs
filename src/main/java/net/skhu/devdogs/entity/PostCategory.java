package net.skhu.devdogs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_category_id")
    private Long id;

    // notice, free, project, active
    @Column(name = "post_category_name")
    private String name;

    @Builder
    public PostCategory(String name) {
        this.name = name;
    }
}
