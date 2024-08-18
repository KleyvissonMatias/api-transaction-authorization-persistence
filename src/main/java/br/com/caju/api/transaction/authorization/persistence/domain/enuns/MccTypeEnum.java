package br.com.caju.api.transaction.authorization.persistence.domain.enuns;

import br.com.caju.api.transaction.authorization.persistence.domain.Mcc;
import lombok.Getter;

@Getter
public enum MccTypeEnum {

    FOOD("FOOD", "5411", "5412"),
    MEAL("MEAL", "5811", "5812"),
    CASH("CASH");

    private final String category;
    private final String[] values;

    MccTypeEnum(String category, String... values) {
        this.category = category;
        this.values = values;
    }

    public static MccTypeEnum fromValue(String mccCodeValue) {
        for (MccTypeEnum mccCategoryType : values()) {
            if (mccCategoryType == CASH || contains(mccCategoryType.values, mccCodeValue)) {
                return mccCategoryType;
            }
        }
        throw new IllegalArgumentException("Invalid MCC Code: " + mccCodeValue);
    }

    private static boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
