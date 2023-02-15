package limjustin.playlist.domain.artist;

import lombok.Getter;

@Getter
public enum Genre {

    BALLAD("발라드"), POP("팝"), HIPHOP("힙합"),
    DANCE("댄스"), RNB("알앤비"), TROT("트로트");

    private final String displayValue;

    private Genre (String displayValue) {
        this.displayValue = displayValue;
    }
}
