package limjustin.playlist.domain.artist;

import javax.persistence.*;

@Entity
@Table(name = "ARTIST")
public class Artist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;  // PK

    @Enumerated(EnumType.STRING)
    private Type type;  // 타입 [남자솔로, 여자솔로, 남자그룹, 여자그룹]

    @Enumerated(EnumType.STRING)
    private Genre genre;  // 장르 [발라드, 팝, 힙합, 댄스, 알앤비, 트로트]

    private String name;  // 아티스트 이름
    private String profileImg;  // 프로필 이미지
}
