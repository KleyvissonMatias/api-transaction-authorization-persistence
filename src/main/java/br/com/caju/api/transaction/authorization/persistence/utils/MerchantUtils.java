package br.com.caju.api.transaction.authorization.persistence.utils;


public class MerchantUtils {

    public static String extractMerchantName(String merchantDetails) {
        return merchantDetails.length() > 20 ? merchantDetails.substring(0, 20) : merchantDetails;
    }
}
