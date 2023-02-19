package limjustin.playlist.dto.music;

import limjustin.playlist.domain.artist.Artist;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MusicFormDto {

    @NotEmpty(message = "노래 제목은 필수로 작성하세요.")
    private String title;  // 노래 제목

    // FormDto 에서 Null 관련 처리가 조금 그렇다 더 생각해보기!
    // @NotNull(message = "아티스트는 필수로 선택하세요.")
    private Artist artist;  // 아티스트

    @NotEmpty(message = "노래 가사는 필수로 작성하세요.")
    private String lyrics;  // 노래 가사

    private String link;  // 유튜브 링크
    private byte[] coverImg;  // 앨범 커버 이미지
}
