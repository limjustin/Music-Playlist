package limjustin.playlist.web;

import limjustin.playlist.dto.music.MusicSearchDto;
import limjustin.playlist.dto.playlist.PlaylistFormDto;
import limjustin.playlist.service.MusicService;
import limjustin.playlist.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    private final MusicService musicService;

    @GetMapping("/playlist/new")
    public String createPlaylistForm(Model model) {
        List<MusicSearchDto> musicList = musicService.findAllMusicSearchDto();

        CreationDto creationDto = new CreationDto();
        creationDto.setPlaylist(musicList);

        model.addAttribute("formDto", new PlaylistFormDto());
        model.addAttribute("creationDto", creationDto);
        return "playlist/createPlaylist";
    }

    private class CreationDto {
        private List<MusicSearchDto> playlist;

        public List<MusicSearchDto> getPlaylist() {
            return playlist;
        }

        public void setPlaylist(List<MusicSearchDto> playlist) {
            this.playlist = playlist;
        }
    }
}
