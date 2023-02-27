package limjustin.playlist.dto.playlist;

import limjustin.playlist.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PlaylistFormDto {

    @NotEmpty(message = "플레이리스트 제목은 필수로 작성하세요.")
    private String name;  // 플레이리스트 제목

    private User user;  // 회원
}
