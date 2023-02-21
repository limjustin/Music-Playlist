package limjustin.playlist.service;

import limjustin.playlist.domain.user.UserRepository;
import limjustin.playlist.dto.user.UserSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;

    @Test
    void 회원_로그인() {
        // given
        UserSaveDto user = new UserSaveDto();

        String inputName = "charlie";
        String inputPassword = "1234";

        user.setName(inputName);
        user.setPassword(inputPassword);

        userService.join(user);

        // when
        Long userId = userService.check(inputName, inputPassword);

        // then
        assertThat(userId).isEqualTo(1L);
    }

}