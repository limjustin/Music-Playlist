package limjustin.playlist.domain.user;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("운영자"), GUEST("사용자");

    private final String displayValue;

    Role(String displayValue) {
        this.displayValue = displayValue;
    }
}
