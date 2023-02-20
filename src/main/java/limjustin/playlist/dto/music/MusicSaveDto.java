package limjustin.playlist.dto.music;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.music.Music;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MusicSaveDto {

    private String title;  // 노래 제목
    private Artist artist;  // 아티스트
    private String lyrics;  // 노래 가사
    private String link;  // 유튜브 링크
    private byte[] coverImg;  // 앨범 커버 이미지

    public Music toEntity() {
        Music music = new Music(artist, title, lyrics, coverImg, link);
        music.setArtist(artist);
        return music;
    }
}
