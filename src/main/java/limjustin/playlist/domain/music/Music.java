package limjustin.playlist.domain.music;

import limjustin.playlist.domain.artist.Artist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name = "MUSIC")
public class Music {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long id;  // PK

    @ManyToOne @NotNull
    @JoinColumn(name = "artist_id")
    private Artist artist;  // 아티스트

    @NotNull
    @Column(length = 100)
    private String title;  // 노래 제목

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String lyrics;  // 노래 가사

    @Lob
    private byte[] coverImg;  // 앨범 커버 이미지

    private String link;  // 유튜브 링크

    public Music(Artist artist, String title, String lyrics, byte[] coverImg, String link) {
        this.artist = artist;
        this.title = title;
        this.lyrics = lyrics;
        this.coverImg = coverImg;
        this.link = link;
    }

    // 연관관계 편의 메서드 (setArtist)
    public void setArtist(Artist artist) {
        this.artist = artist;
        artist.getMusics().add(this);
    }
}
