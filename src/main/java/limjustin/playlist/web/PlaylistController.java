package limjustin.playlist.web;

import limjustin.playlist.domain.music.Music;
import limjustin.playlist.domain.playlist.LinkedTable;
import limjustin.playlist.domain.playlist.Playlist;
import limjustin.playlist.domain.user.User;
import limjustin.playlist.dto.music.MusicSearchDto;
import limjustin.playlist.dto.playlist.PlaylistFormDto;
import limjustin.playlist.service.MusicService;
import limjustin.playlist.service.PlaylistService;
import limjustin.playlist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    private final MusicService musicService;
    private final UserService userService;

    @GetMapping("/playlist/new")
    public String createPlaylistForm(Model model) {
        CreationDto creationDto = new CreationDto();
        creationDto.setMusics(musicService.findAllMusicSearchDto());

        model.addAttribute("musicForm", creationDto);
        model.addAttribute("playlistForm", new PlaylistFormDto());

        return "playlist/createPlaylist";
    }

    @PostMapping("/playlist/new")
    public String createPlaylist(@ModelAttribute("musicForm") CreationDto form, @ModelAttribute("playlistForm") PlaylistFormDto formDto, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        User currentUser = userService.getUser(userId);

        List<MusicSearchDto> musics = form.getMusics();

        Playlist playlist = new Playlist(currentUser, formDto.getName());
        playlistService.makePlaylist(playlist);

        for (MusicSearchDto music : musics) {
            if(music.getCheck()) {
                Music findMusic = musicService.findOneMusicByTitle(music.getTitle());  // 일단 타이틀로 찾았음
                LinkedTable linkedTable = new LinkedTable(findMusic, playlist);
                playlist.addList(linkedTable);
                playlistService.makeLinkedTable(linkedTable);
            }
        }

        return "redirect:/main";
    }

    // 플레이리스트 수정 및 삭제도 가능해야 한다!

    // 동작 완료되면 dto 부분으로 따로 클래스 만들어주기
    public class CreationDto {
        private List<MusicSearchDto> musics;

        public List<MusicSearchDto> getMusics() {
            return musics;
        }

        public void setMusics(List<MusicSearchDto> musics) {
            this.musics = musics;
        }
    }
}
