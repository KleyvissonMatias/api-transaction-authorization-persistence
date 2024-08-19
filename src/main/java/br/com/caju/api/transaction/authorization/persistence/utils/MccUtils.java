package br.com.caju.api.transaction.authorization.persistence.utils;

import br.com.caju.api.transaction.authorization.persistence.domain.enuns.MccTypeEnum;

import java.util.Arrays;
import java.util.List;

import static br.com.caju.api.transaction.authorization.persistence.utils.MerchantUtils.extractMerchantName;

public class MccUtils {

    private static final List<MccTypeEnum> allowedMccTypes = Arrays.asList(MccTypeEnum.FOOD, MccTypeEnum.MEAL);

    public static String getMerchantOrMcc(String mccCode, String merchant) {
        MccTypeEnum mccType = MccTypeEnum.fromValue(mccCode);

        if (mccType == null || !allowedMccTypes.contains(mccType)) {
            return extractMerchantName(merchant);
        } else {
            return mccCode;
        }
    }
}