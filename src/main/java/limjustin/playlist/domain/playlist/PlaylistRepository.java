package limjustin.playlist.domain.playlist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PlaylistRepository {

    @PersistenceContext  // EntityManager 주입
    private EntityManager em;

    public void savePlaylist(Playlist playlist) {
        em.persist(playlist);
    }

    public void saveLinkedTable(LinkedTable linkedTable) {
        em.persist(linkedTable);
    }
}
