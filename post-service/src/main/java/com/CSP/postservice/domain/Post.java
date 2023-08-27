package com.CSP.postservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @SequenceGenerator(name = "postSeq", sequenceName = "postSeq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "postSeq")
    int post_id;
    int owner_id;
    int noOfLikes;
    String heading,content;
    List<Integer> like_ids;
}
