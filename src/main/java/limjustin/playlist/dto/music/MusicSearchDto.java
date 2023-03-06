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
public class MusicSearchDto {

    private Long id;  // PK
    private String title;  // 노래 제목
    private Artist artist;  // 아티스트
    private byte[] coverImg;  // 앨범 커버 이미지
    private Boolean check;
    private List<MusicSearchDto> searchDtoList = new ArrayList<>();

    public MusicSearchDto(Long id, String title, Artist artist, byte[] coverImg, Boolean check) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.coverImg = coverImg;
        this.check = check;
    }

    public MusicSearchDto(List<Music> musics) {
        for(Music music : musics) {
            this.id = music.getId();
            this.title = music.getTitle();
            this.artist = music.getArtist();
            this.coverImg = music.getCoverImg();

            searchDtoList.add(new MusicSearchDto(id, title, artist, coverImg, check));
        }
    }
}
