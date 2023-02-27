package limjustin.playlist.domain.playlist;

import limjustin.playlist.domain.user.User;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Table(name = "PLAYLIST")
public class Playlist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long id;  // PK

    @ManyToOne @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(length = 100)
    private String name;  // 플레이리스트 제목

    @OneToMany(mappedBy = "playlist")  // 조회용 리스트 (Playlist -> Music)
    private List<LinkedTable> musics = new ArrayList<>();

    // 연관관계 편의 메서드 (setUser)
    public void setUser(User user) {
        this.user = user;
        user.getPlaylists().add(this);
    }
}
