package limjustin.playlist.domain.music;

import limjustin.playlist.domain.artist.Artist;

import javax.persistence.*;

@Entity
@Table(name = "MUSIC")
public class Music {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;  // PK

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;  // 아티스트

    private String title;  // 노래 제목
    private String lyrics;  // 노래 가사
    private String link;  // 유튜브 링크
    private String coverImg;  // 앨범 커버 이미지
}
