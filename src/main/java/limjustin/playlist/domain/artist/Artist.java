package limjustin.playlist.domain.artist;

import limjustin.playlist.domain.music.Music;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name = "Artist")
public class Artist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;  // PK

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;  // 타입 [남자솔로, 여자솔로, 남자그룹, 여자그룹]

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;  // 장르 [발라드, 팝, 힙합, 댄스, 알앤비, 트로트]

    @NotNull
    @Column(length = 20)
    private String name;  // 아티스트 이름

    @Lob
    private byte[] profileImg;  // 프로필 이미지

    @OneToMany(mappedBy = "artist", cascade = CascadeType.REMOVE)  // 조회용 리스트 (Artist -> Music)
    private List<Music> musics = new ArrayList<>();

    public Artist(String name, Type type, Genre genre, byte[] profileImg) {
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.profileImg = profileImg;
    }
}
