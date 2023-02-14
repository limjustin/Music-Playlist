package limjustin.playlist.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Table(name = "USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;  // PK

    @NotNull
    @Column(length = 20)
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists = new ArrayList<>();
}
