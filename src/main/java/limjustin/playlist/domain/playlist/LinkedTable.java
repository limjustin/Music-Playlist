package limjustin.playlist.domain.playlist;

import limjustin.playlist.domain.music.Music;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name = "LINKED_TABLE")
public class LinkedTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linked_table_id")
    private Long id;  // PK

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    public LinkedTable(Music music, Playlist playlist) {
        this.music = music;
        this.playlist = playlist;
    }
}
