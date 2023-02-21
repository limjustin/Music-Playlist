package limjustin.playlist.dto.user;

import limjustin.playlist.domain.user.Role;
import limjustin.playlist.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSaveDto {

    private String name;  // 이름
    private String password;  // 패스워드
    private Role role;  // 권한 [운영자, 사용자]

    public User toEntity() {
        return new User(name, password, role);
    }
}
