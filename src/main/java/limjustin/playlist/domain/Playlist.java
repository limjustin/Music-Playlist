package limjustin.playlist.domain;

import javax.persistence.*;

@Entity
@Table(name = "PLAYLIST")
public class Playlist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long id;  // PK
}
