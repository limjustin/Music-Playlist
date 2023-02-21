package limjustin.playlist.web;

import limjustin.playlist.dto.artist.ArtistFormDto;
import limjustin.playlist.dto.artist.ArtistResponseDto;
import limjustin.playlist.dto.artist.ArtistSaveDto;
import limjustin.playlist.service.ArtistService;
import limjustin.playlist.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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
    public String createArtist(@ModelAttribute("formDto") @Valid ArtistFormDto formDto, BindingResult result, @RequestParam("file") MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            return "artist/createArtist";
        }

        ArtistSaveDto artist = new ArtistSaveDto();

        artist.setName(formDto.getName());
        artist.setType(formDto.getType());
        artist.setGenre(formDto.getGenre());
        artist.setProfileImg(ImageUtils.compressImage(file.getBytes()));

        artistService.join(artist);
        return "redirect:/main";
    }

    @GetMapping("/artist")
    public String findArtists(Model model) {
        List<ArtistResponseDto> artistList = artistService.findAllArtist();
        model.addAttribute("artistList", artistList);
        return "artist/findArtist";
    }

    @GetMapping("/artist/img/{fileName}")
    public ResponseEntity<byte[]> getProfileImg(@PathVariable("fileName") Long id) {
        ArtistResponseDto findArtist = artistService.findOneArtistById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(findArtist.getProfileImg());
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
        formDto.setProfileImg(findArtist.getProfileImg());  // 고려해보기

        model.addAttribute("formDto", formDto);
        return "artist/updateArtist";
    }

    @PostMapping("/artist/{id}/edit")
    public String updateArtist(@PathVariable Long id, @ModelAttribute("formDto") ArtistFormDto formDto, @RequestParam("file") MultipartFile file) throws IOException {
        artistService.update(id, formDto.getName(), formDto.getType(), formDto.getGenre(), ImageUtils.compressImage(file.getBytes()));
        return "redirect:/artist";
    }
}
