package limjustin.playlist.dto.user;

import limjustin.playlist.domain.user.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserFormDto {

    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String name;  // 이름

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password;  // 패스워드

    private Role role;  // 권한 [운영자, 사용자]
}
