package com.egintra.common.utils.bank;

public class BankDto {

    private String encryptBody;

    private String sign;

    public String getEncryptBody() {
        return encryptBody;
    }

    public void setEncryptBody(String encryptBody) {
        this.encryptBody = encryptBody;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "BankDto{" +
                "encryptBody='" + encryptBody + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
