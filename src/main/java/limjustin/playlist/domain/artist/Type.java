package limjustin.playlist.domain.artist;

import lombok.Getter;

@Getter
public enum Type {

    MAN_SOLO("남자 솔로"), WOMAN_SOLO("여자 솔로"),
    MAN_GROUP("남자 그룹"), WOMAN_GROUP("여자 그룹");

    private final String displayValue;

    private Type (String displayValue) {
        this.displayValue = displayValue;
    }
}
