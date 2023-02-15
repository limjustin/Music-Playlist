package limjustin.playlist.domain.music;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.artist.ArtistRepository;
import limjustin.playlist.domain.artist.Genre;
import limjustin.playlist.domain.artist.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MusicRepositoryTest {

    @Autowired MusicRepository musicRepository;
    @Autowired ArtistRepository artistRepository;

//    @Test
//    void 음악_등록() {
//        // given
//        Artist artist = new Artist();
//        artist.setName("ITZY");
//        artist.setGenre(Genre.POP);
//        artist.setType(Type.WOMAN_GROUP);
//        artistRepository.save(artist);
//
//        Artist findArtist = artistRepository.findOneById(artist.getId());
//
//        Music music = new Music();
//        music.setArtist(findArtist);
//        music.setTitle("SNEAKERS");
//        music.setLyrics("Put my sneakers on!");
//
//        // when
//        musicRepository.save(music);
//
//        // then
//        Music findMusic = musicRepository.findOneByTitle(music.getTitle());
//        assertThat(findMusic).isEqualTo(music);
//    }
}