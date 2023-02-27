package limjustin.playlist.domain.user;

import limjustin.playlist.domain.playlist.Playlist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;  // PK

    @NotNull
    @Column(length = 20)
    private String name;  // 이름

    @NotNull
    @Column(length = 20)
    private String password;  // 패스워드

    @Enumerated(EnumType.STRING)
    private Role role;  // 권한 [운영자, 사용자]

    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists = new ArrayList<>();

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
