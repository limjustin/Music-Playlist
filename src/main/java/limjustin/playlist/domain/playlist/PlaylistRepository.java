package limjustin.playlist.domain.playlist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<Playlist> findPlaylistsByUserId(Long id) {
        return em.createQuery("SELECT p FROM Playlist p WHERE p.user.id = :id", Playlist.class)
                .setParameter("id", id)
                .getResultList();
    }
}
