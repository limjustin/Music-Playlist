package limjustin.playlist.dto.artist;

import limjustin.playlist.domain.artist.Genre;
import limjustin.playlist.domain.artist.Type;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ArtistFormDto {  //

    @NotEmpty(message = "아티스트 이름은 필수로 작성하세요.")  // String 타입은 NotEmpty
    private String name;  // 아티스트 이름

    @NotNull(message = "아티스트 타입은 필수로 지정하세요.")  // 나머지 타입은 NotNull
    private Type type;  // 타입 [남자솔로, 여자솔로, 남자그룹, 여자그룹]

    @NotNull(message = "아티스트 장르는 필수로 지정하세요.")
    private Genre genre;  // 장르 [발라드, 팝, 힙합, 댄스, 알앤비, 트로트]

    private String profileImg;  // 프로필 이미지
}
