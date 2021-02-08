package net.joins.domain.entity;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_POST")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    Long id;

    @Column(name = "LINK")
    String link;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Column(name = "SUBTITLE")
    String subtitle;
    
    @Column(name = "WRITTER", nullable = false)
    String writter;
    
    @Column(name = "UPDATED")
    @UpdateTimestamp
    Timestamp updated;
    
    @Column(name = "CONTENT")
    String content;
    
    @Column(name = "BG_IMAGE")
    String bgImage;
}