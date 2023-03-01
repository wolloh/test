package relex.utils;

import java.util.Arrays;
import java.util.Optional;

public enum WalletTypeEnum {
    BTC(1),
    TON(2),
    RUB(3);
    private final int value;
    WalletTypeEnum(int value) {
        this.value = value;
    }

    public static Optional<WalletTypeEnum> valueOf(int value) {
        return Arrays.stream(values())
                .filter(legNo -> legNo.value == value)
                .findFirst();
    }
}
