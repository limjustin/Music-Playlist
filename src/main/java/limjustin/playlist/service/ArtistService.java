package limjustin.playlist.service;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.artist.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Transactional
    public void join(Artist artist) {
        artistRepository.save(artist);
    }

    public Artist findOneArtistById(Long id) {
        return artistRepository.findOneById(id);
    }

    public Artist findOneArtistByName(String name) {
        return artistRepository.findOneByName(name);
    }

    public List<Artist> findAllArtist() {
        return artistRepository.findAll();
    }
}
