package limjustin.playlist.service;

import limjustin.playlist.ImageUtils;
import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.artist.ArtistRepository;
import limjustin.playlist.domain.artist.Genre;
import limjustin.playlist.domain.artist.Type;
import limjustin.playlist.dto.artist.ArtistResponseDto;
import limjustin.playlist.dto.artist.ArtistSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Transactional
    public void join(ArtistSaveDto artist) {
        artistRepository.save(artist.toEntity());  // DTO 말고 엔티티를 저장해야하므로 DB에 들어가기 전에 DTO -> Artist 엔티티로 변환
    }

    public ArtistResponseDto findOneArtistById(Long id) {
        Artist findArtist = artistRepository.findOneById(id);
        byte[] fileImage = ImageUtils.decompressImage(findArtist.getProfileImg());

        return new ArtistResponseDto(findArtist.getId(), findArtist.getName(), findArtist.getType(), findArtist.getGenre(), fileImage);
    }

    public Artist findOneArtistByName(String name) {
        return artistRepository.findOneByName(name);
    }

    public List<ArtistResponseDto> findAllArtist() {
        List<Artist> findList = artistRepository.findAll();
        ArtistResponseDto responseDto = new ArtistResponseDto(findList);  // 웹에서 보여지기 전에 Entity -> DTO 변환
        return responseDto.getResponseDtoList();
    }

    @Transactional
    public void update(Long id, String name, Type type, Genre genre, byte[] profileImg) {
        Artist findArtist = artistRepository.findOneById(id);
        findArtist.setName(name);  // 변경 감지
        findArtist.setType(type);
        findArtist.setGenre(genre);
        findArtist.setProfileImg(profileImg);
    }

    @Transactional
    public void delete(Long id) {
        Artist findArtist = artistRepository.findOneById(id);
        artistRepository.remove(findArtist);
    }
}
