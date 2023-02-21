package limjustin.playlist.dto.music;

import limjustin.playlist.domain.artist.Artist;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MusicFormDto {

    @NotEmpty(message = "노래 제목은 필수로 작성하세요.")
    private String title;  // 노래 제목

    private Artist artist;  // 아티스트
    private String lyrics;  // 노래 가사
    private String link;  // 유튜브 링크
    private byte[] coverImg;  // 앨범 커버 이미지
}
