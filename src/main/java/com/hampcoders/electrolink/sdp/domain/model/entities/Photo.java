package com.hampcoders.electrolink.sdp.domain.model.entities;

import com.hampcoders.electrolink.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Photo extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoId;
    private String url;

    public Photo(String photoId, String url) {
        this.photoId = photoId;
        this.url = url;
    }
}
