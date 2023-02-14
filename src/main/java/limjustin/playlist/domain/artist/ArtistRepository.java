package limjustin.playlist.domain.artist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ArtistRepository {

    @PersistenceContext  // EntityManager 주입
    private EntityManager em;

    public void save(Artist artist) {
        em.persist(artist);
    }

    public Artist findOneById(Long id) {
        return em.find(Artist.class, id);
    }

    public Artist findOneByName(String name) {
        return em.createQuery("SELECT a from Artist a WHERE a.name = :name", Artist.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Artist> findAll() {
        return em.createQuery("SELECT a from Artist a", Artist.class)
                .getResultList();
    }
}
