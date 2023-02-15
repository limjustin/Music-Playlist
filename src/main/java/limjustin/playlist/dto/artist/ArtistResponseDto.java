package limjustin.playlist.dto.artist;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.artist.Genre;
import limjustin.playlist.domain.artist.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ArtistResponseDto {  // 조회 용도 DTO 객체

    private Long id;  // PK
    private String name;  // 아티스트 이름
    private Type type;  // 타입 [남자솔로, 여자솔로, 남자그룹, 여자그룹]
    private Genre genre;  // 장르 [발라드, 팝, 힙합, 댄스, 알앤비, 트로트]
    private String profileImg;  // 프로필 이미지

    public List<ArtistResponseDto> responseDtoList = new ArrayList<>();

    private ArtistResponseDto(Long id, String name, Type type, Genre genre, String profileImg) {  // Constructor
        this.id = id;
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.profileImg = profileImg;
    }

    public ArtistResponseDto(Artist artist) {  // Entity to DTO
        this.id = artist.getId();
        this.name = artist.getName();
        this.type = artist.getType();
        this.genre = artist.getGenre();
        this.profileImg = artist.getProfileImg();
    }

    public ArtistResponseDto(List<Artist> artists) {  // List<Entity> to List<DTO>
        for (Artist artist : artists) {
            this.id = artist.getId();
            this.name = artist.getName();
            this.type = artist.getType();
            this.genre = artist.getGenre();
            this.profileImg = artist.getProfileImg();

            responseDtoList.add(new ArtistResponseDto(id, name, type, genre, profileImg));
        }
    }
}
