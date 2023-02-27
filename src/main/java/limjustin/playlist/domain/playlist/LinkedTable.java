package limjustin.playlist.domain.playlist;

import limjustin.playlist.domain.music.Music;

import javax.persistence.*;

@Entity
@Table(name = "LINKED_TABLE")
public class LinkedTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linked_table_id")
    private Long id;  // PK

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}
