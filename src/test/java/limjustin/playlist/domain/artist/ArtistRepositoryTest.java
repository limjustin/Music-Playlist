package limjustin.playlist.domain.artist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ArtistRepositoryTest {

    @Autowired ArtistRepository artistRepository;

    @Test
    void 아티스트_등록() {
        // given
        Artist artist = makeArtist("ITZY", Genre.POP, Type.WOMAN_GROUP);

        // when
        artistRepository.save(artist);

        // then
        Artist findArtist = artistRepository.findOneById(artist.getId());
        assertThat(findArtist).isEqualTo(artist);
    }

    @Test
    void 아티스트_모두_조회() {
        // given
        Artist artist1 = makeArtist("ITZY", Genre.POP, Type.WOMAN_GROUP);
        Artist artist2 = makeArtist("IVE", Genre.DANCE, Type.WOMAN_GROUP);
        Artist artist3 = makeArtist("ZICO", Genre.HIPHOP, Type.MAN_SOLO);

        artistRepository.save(artist1);
        artistRepository.save(artist2);
        artistRepository.save(artist3);

        // when
        List<Artist> findArtists = artistRepository.findAll();

        // then
        assertThat(findArtists.get(0)).isEqualTo(artist1);
        assertThat(findArtists.get(1)).isEqualTo(artist2);
        assertThat(findArtists.get(2)).isEqualTo(artist3);
    }

    private Artist makeArtist(String name, Genre genre, Type type) {
        Artist artist = new Artist();
        artist.setName(name);
        artist.setGenre(genre);
        artist.setType(type);
        return artist;
    }
}