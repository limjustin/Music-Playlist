package limjustin.playlist.service;

import limjustin.playlist.domain.music.Music;
import limjustin.playlist.domain.music.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    @Transactional
    public void join(Music music) {
        musicRepository.save(music);
    }

    public Music findOneMusicById(Long id) {
        return musicRepository.findOneById(id);
    }

    public Music findOneMusicByTitle(String title) {
        return musicRepository.findOneByTitle(title);
    }

    public List<Music> findAllMusic() {
        return musicRepository.findAll();
    }
}
