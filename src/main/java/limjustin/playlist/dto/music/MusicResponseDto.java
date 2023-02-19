package limjustin.playlist.dto.music;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.music.Music;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MusicResponseDto {

    private Long id;  // PK
    private String title;  // 노래 제목
    private Artist artist;  // 아티스트
    private String lyrics;  // 노래 가사
    private String link;  // 유튜브 링크
    private byte[] coverImg;  // 앨범 커버 이미지

    private List<MusicResponseDto> responseDtoList = new ArrayList<>();

    public MusicResponseDto(Long id, String title, Artist artist, String lyrics, String link, byte[] coverImg) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.lyrics = lyrics;
        this.link = link;
        this.coverImg = coverImg;
    }

    public MusicResponseDto(List<Music> musics) {
        for(Music music : musics) {
            this.id = music.getId();
            this.title = music.getTitle();
            this.artist = music.getArtist();
            this.lyrics = music.getLyrics();
            this.link = music.getLink();
            this.coverImg = music.getCoverImg();

            responseDtoList.add(new MusicResponseDto(id, title, artist, lyrics, link, coverImg));
        }
    }
}
