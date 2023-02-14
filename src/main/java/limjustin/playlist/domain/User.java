package limjustin.playlist.domain;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;  // PK
}
