package limjustin.playlist.service;

import limjustin.playlist.domain.user.User;
import limjustin.playlist.domain.user.UserRepository;
import limjustin.playlist.dto.user.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void join(UserSaveDto user) {
        userRepository.save(user.toEntity());
    }

    public String getName(Long id) {
        User findUser = userRepository.findById(id).get();
        return findUser.getName();
    }

    public User getUser(Long id) {
        return userRepository.findByIdNonOptional(id);
    }

    public Long check(String id, String password) {
        Optional<User> findUser = userRepository.findByNameAndPassword(id, password);

        if (findUser.isEmpty()) {  // 로그인 실패
            return 0L;
        } else {  // 로그인 성공 (id 반환)
            return findUser.get().getId();
        }
    }
}
