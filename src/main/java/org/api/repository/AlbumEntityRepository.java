package org.api.repository;

import org.api.entities.AlbumEntity;
import org.api.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumEntityRepository extends BaseRepository<AlbumEntity, String> {

    public Optional<AlbumEntity> findOneByTypeAlbumAndUserEntityId(String tpeAlbum, String idUser);

    public List<AlbumEntity> findAllByUserEntityId(String id);

}
