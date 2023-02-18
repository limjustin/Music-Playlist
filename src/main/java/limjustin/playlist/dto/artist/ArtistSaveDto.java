package limjustin.playlist.dto.artist;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.artist.Genre;
import limjustin.playlist.domain.artist.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ArtistSaveDto {  // 저장용 DTO 객체

    private String name;  // 아티스트 이름
    private Type type;  // 타입 [남자솔로, 여자솔로, 남자그룹, 여자그룹]
    private Genre genre;  // 장르 [발라드, 팝, 힙합, 댄스, 알앤비, 트로트]
    private byte[] profileImg;  // 프로필 이미지

    public Artist toEntity() {  // DTO to Entity
        return new Artist(name, type, genre, profileImg);
    }
}
