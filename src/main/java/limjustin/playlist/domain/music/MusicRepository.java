package limjustin.playlist.domain.music;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MusicRepository {

    @PersistenceContext  // EntityManager 주입
    private EntityManager em;

    public void save(Music music) {
        em.persist(music);
    }

    public Music findOneById(Long id) {
        return em.find(Music.class, id);
    }

    public Music findOneByTitle(String title) {
        return em.createQuery("SELECT m from Music m WHERE m.title = :title", Music.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    public List<Music> findAll() {
        return em.createQuery("SELECT m from Music m", Music.class)
                .getResultList();
    }

    public void remove(Music music) {
        em.remove(music);
    }
}
