package limjustin.playlist.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserLoginDto {

    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String name;  // 이름

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password;  // 패스워드
}
