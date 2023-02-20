package limjustin.playlist.service;

import limjustin.playlist.ImageUtils;
import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.music.Music;
import limjustin.playlist.domain.music.MusicRepository;
import limjustin.playlist.dto.music.MusicResponseDto;
import limjustin.playlist.dto.music.MusicSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    @Transactional
    public void join(MusicSaveDto music) {
        musicRepository.save(music.toEntity());
    }

    public MusicResponseDto findOneMusicById(Long id) {
        Music findMusic = musicRepository.findOneById(id);
        byte[] fileImage = ImageUtils.decompressImage(findMusic.getCoverImg());

        return new MusicResponseDto(findMusic.getId(), findMusic.getTitle(), findMusic.getArtist(), findMusic.getLyrics(), findMusic.getLink(), fileImage);
    }

    public Music findOneMusicByTitle(String title) {
        return musicRepository.findOneByTitle(title);
    }

    public List<Music> findAllMusic() {
        return musicRepository.findAll();
    }

    @Transactional
    public void update(Long id, String title, Artist artist, String lyrics, String link, byte[] coverImg) {
        Music findMusic = musicRepository.findOneById(id);
        findMusic.setTitle(title);
        findMusic.setArtist(artist);
        findMusic.setLyrics(lyrics);
        findMusic.setLink(link);
        findMusic.setCoverImg(coverImg);
    }

    @Transactional
    public void delete(Long id) {
        Music findMusic = musicRepository.findOneById(id);
        musicRepository.remove(findMusic);
    }
}
