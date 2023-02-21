package limjustin.playlist.domain.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext  // EntityManager 주입
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public Optional<User> findByNameAndPassword(String name, String password) {
        return em.createQuery("SELECT u from User u WHERE u.name = :name AND u.password = :password", User.class)
                .setParameter("name", name)
                .setParameter("password", password)
                .getResultList().stream().findAny();
    }  // Optional 반환의 이유 : 찾는 값이 없으면 null 반환 필요하므로 (일단 이렇게 썼음)
}
