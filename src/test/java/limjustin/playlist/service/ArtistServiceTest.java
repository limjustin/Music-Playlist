package limjustin.playlist.service;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.artist.Genre;
import limjustin.playlist.domain.artist.Type;
import limjustin.playlist.domain.music.Music;
import limjustin.playlist.dto.artist.ArtistSaveDto;
import limjustin.playlist.dto.music.MusicSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ArtistServiceTest {

    @Autowired ArtistService artistService;
    @Autowired MusicService musicService;

    @Test
    void 아티스트_삭제시_음악도_삭제() {
        // given
        ArtistSaveDto artist1 = createArtist("ITZY", Genre.POP, Type.WOMAN_GROUP);
        ArtistSaveDto artist2 = createArtist("IVE", Genre.DANCE, Type.WOMAN_GROUP);
        artistService.join(artist1);
        artistService.join(artist2);

        Artist findArtist1 = artistService.findOneArtistByName(artist1.getName());
        Artist findArtist2 = artistService.findOneArtistByName(artist2.getName());

        MusicSaveDto music1 = createMusic("Cheshire", findArtist1, "Why so serious?", "link1");
        MusicSaveDto music2 = createMusic("Dalla Dalla", findArtist1, "I love myself", "link2");
        MusicSaveDto music3 = createMusic("ELEVEN", findArtist2, "You make me feel like eleven", "link3");
        MusicSaveDto music4 = createMusic("LOVE DIVE", findArtist2, "숨 참아!", "link4");
        musicService.join(music1);
        musicService.join(music2);
        musicService.join(music3);
        musicService.join(music4);

        List<Music> musics_before = musicService.findAllMusic();

        // when
        artistService.delete(findArtist1.getId());

        // then
        List<Music> musics_after = musicService.findAllMusic();
        assertThat(musics_before.size()).isEqualTo(4);
        assertThat(musics_after.size()).isEqualTo(2);
    }

    private ArtistSaveDto createArtist(String name, Genre genre, Type type) {
        ArtistSaveDto artist = new ArtistSaveDto();
        artist.setName(name);
        artist.setGenre(genre);
        artist.setType(type);
        return artist;
    }

    private MusicSaveDto createMusic(String title, Artist artist, String lyrics, String link) {
        MusicSaveDto music = new MusicSaveDto();
        music.setTitle(title);
        music.setArtist(artist);
        music.setLyrics(lyrics);
        music.setLink(link);
        return music;
    }
}