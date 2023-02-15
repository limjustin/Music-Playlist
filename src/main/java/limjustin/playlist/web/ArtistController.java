package limjustin.playlist.web;

import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.dto.artist.ArtistFormDto;
import limjustin.playlist.dto.artist.ArtistResponseDto;
import limjustin.playlist.dto.artist.ArtistSaveDto;
import limjustin.playlist.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/artist/new")
    public String createArtistForm(Model model) {
        model.addAttribute("formDto", new ArtistFormDto());
        return "artist/createArtist";
    }

    @PostMapping("/artist/new")
    public String createArtist(@Valid ArtistFormDto formDto) {
        ArtistSaveDto artist = new ArtistSaveDto();

        artist.setName(formDto.getName());
        artist.setType(formDto.getType());
        artist.setGenre(formDto.getGenre());
        artist.setProfileImg(formDto.getProfileImg());

        artistService.join(artist);
        return "redirect:/main";
    }

    @GetMapping("/artist")
    public String findArtists(Model model) {
        List<ArtistResponseDto> artistList = artistService.findAllArtist();
        model.addAttribute("artistList", artistList);
        return "artist/findArtist";
    }

    @GetMapping("/artist/{id}")
    public String selectArtist(@PathVariable Long id, Model model) {
        ArtistResponseDto artist = artistService.findOneArtistById(id);
        model.addAttribute("artist", artist);
        return "artist/selectArtist";
    }

    @GetMapping("/artist/{id}/edit")
    public String updateArtistForm(@PathVariable Long id, Model model) {
        ArtistResponseDto findArtist = artistService.findOneArtistById(id);

        ArtistFormDto formDto = new ArtistFormDto();
        formDto.setName(findArtist.getName());
        formDto.setType(findArtist.getType());
        formDto.setGenre(findArtist.getGenre());
        formDto.setProfileImg(findArtist.getProfileImg());

        model.addAttribute("formDto", formDto);
        return "artist/updateArtist";
    }

    @PostMapping("/artist/{id}/edit")
    public String updateArtist(@PathVariable Long id, @ModelAttribute("formDto") ArtistFormDto formDto) {
        artistService.update(id, formDto.getName(), formDto.getType(), formDto.getGenre(), formDto.getProfileImg());
        return "redirect:/artist";
    }
}
