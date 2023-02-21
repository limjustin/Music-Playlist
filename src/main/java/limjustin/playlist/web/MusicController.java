package limjustin.playlist.web;

import limjustin.playlist.ImageUtils;
import limjustin.playlist.domain.artist.Artist;
import limjustin.playlist.domain.music.Music;
import limjustin.playlist.dto.artist.ArtistResponseDto;
import limjustin.playlist.dto.music.MusicFormDto;
import limjustin.playlist.dto.music.MusicResponseDto;
import limjustin.playlist.dto.music.MusicSaveDto;
import limjustin.playlist.service.ArtistService;
import limjustin.playlist.service.MusicService;
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
public class MusicController {

    private final MusicService musicService;
    private final ArtistService artistService;

    @GetMapping("/music/new")
    public String createMusicForm(Model model) {
        List<ArtistResponseDto> artists = artistService.findAllArtist();

        model.addAttribute("formDto", new MusicFormDto());
        model.addAttribute("artists", artists);
        return "music/createMusic";
    }

    @PostMapping("/music/new")
    public String createMusic(Model model, @ModelAttribute("formDto") @Valid MusicFormDto formDto, BindingResult result, @RequestParam("artistId") Long artistId, @RequestParam("file") MultipartFile file) throws IOException {

        if(result.hasErrors()) {
            List<ArtistResponseDto> artists = artistService.findAllArtist();
            model.addAttribute("artists", artists);
            return "music/createMusic";
        }

        MusicSaveDto music = new MusicSaveDto();
        Artist findArtist = artistService.findOneArtistEntityById(artistId);

        music.setTitle(formDto.getTitle());
        music.setArtist(findArtist);  // RequestParam 통해 artistId 받고 실제 Entity 넣어주어야 안 그러면 Null Exception
        music.setLyrics(formDto.getLyrics());
        music.setLink(formDto.getLink());
        music.setCoverImg(ImageUtils.compressImage(file.getBytes()));

        musicService.join(music);
        return "redirect:/main";
    }

    @GetMapping("/music")
    public String findMusics(Model model) {
        List<Music> musicList = musicService.findAllMusic();
        model.addAttribute("musicList", musicList);
        return "music/findMusic";
    }

    @GetMapping("/music/img/{fileName}")
    public ResponseEntity<byte[]> getCoverImg(@PathVariable("fileName") Long id) {
        MusicResponseDto findMusic = musicService.findOneMusicById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(findMusic.getCoverImg());
    }

    @GetMapping("/music/{id}")
    public String findMusic(@PathVariable Long id, Model model) {
        MusicResponseDto music = musicService.findOneMusicById(id);
        model.addAttribute("music", music);
        return "music/selectMusic";
    }

    @GetMapping("/music/{id}/edit")
    public String updateMusicForm(@PathVariable Long id, Model model) {
        MusicResponseDto findMusic = musicService.findOneMusicById(id);

        List<ArtistResponseDto> artists = artistService.findAllArtist();
        model.addAttribute("artists", artists);

        MusicFormDto formDto = new MusicFormDto();

        formDto.setTitle(findMusic.getTitle());
        formDto.setArtist(findMusic.getArtist());
        formDto.setLyrics(findMusic.getLyrics());
        formDto.setLink(findMusic.getLink());
        formDto.setCoverImg(findMusic.getCoverImg());

        model.addAttribute("formDto", formDto);
        return "music/updateMusic";
    }

    @PostMapping("/music/{id}/edit")
    public String updateMusic(@PathVariable Long id, @ModelAttribute("formDto") MusicFormDto formDto, @RequestParam("artistId") Long artistId, @RequestParam("file") MultipartFile file) throws IOException {
        Artist findArtist = artistService.findOneArtistEntityById(artistId);

        musicService.update(id, formDto.getTitle(), findArtist, formDto.getLyrics(), formDto.getLink(), ImageUtils.compressImage(file.getBytes()));
        return "redirect:/music";
    }
}
