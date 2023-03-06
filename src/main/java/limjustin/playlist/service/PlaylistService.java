package limjustin.playlist.service;

import limjustin.playlist.domain.playlist.LinkedTable;
import limjustin.playlist.domain.playlist.Playlist;
import limjustin.playlist.domain.playlist.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Transactional
    public void makePlaylist(Playlist playlist) {
        playlistRepository.savePlaylist(playlist);
    }

    @Transactional
    public void makeLinkedTable(LinkedTable linkedTable) {
        playlistRepository.saveLinkedTable(linkedTable);
    }
}
