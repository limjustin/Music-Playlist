package limjustin.playlist.service;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.artist.Genre;
import limjustin.playlist.domain.artist.Type;
import limjustin.playlist.dto.artist.ArtistSaveDto;
import limjustin.playlist.dto.music.MusicSaveDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MusicServiceTest {

    @Autowired MusicService musicService;
    @Autowired ArtistService artistService;

    @Test
    void Artist_조회용_리스트() {
        // given
        ArtistSaveDto artist = createArtist("ITZY", Genre.POP, Type.WOMAN_GROUP);
        artistService.join(artist);

        // when
        Artist findArtist = artistService.findOneArtistByName(artist.getName());

        MusicSaveDto music1 = createMusic("Cheshire", findArtist, "Why so serious?", "link1");
        MusicSaveDto music2 = createMusic("Mafia", findArtist, "I'm the Mafia", "link2");

        musicService.join(music1);
        musicService.join(music2);

        // then
        assertThat(findArtist.getMusics().size()).isEqualTo(2);
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