package com.robertciotoiu.newscommons.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NonNull
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @NonNull
    @Column(name="published_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime publishedDate;
    @Column(name="update_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updateDate;
    @Column(name="image_url")
    private String imageUrl;
}
