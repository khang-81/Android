package com.example.bank_donga;

public class ExchangeRate {
    private String currency;
    private String buyTransfer;
    private String sellTransfer;
    private String buyCash;
    private String sellCash;
    private int flagResId; // Thêm trường mới cho quốc kỳ

    // Constructor cũ (nếu cần giữ lại cho tương thích)
    public ExchangeRate(String currency, String buyTransfer, String sellTransfer,
                        String buyCash, String sellCash) {
        this.currency = currency;
        this.buyTransfer = buyTransfer;
        this.sellTransfer = sellTransfer;
        this.buyCash = buyCash;
        this.sellCash = sellCash;
        this.flagResId = 0; // Giá trị mặc định
    }

    // Constructor mới với tham số flagResId
    public ExchangeRate(String currency, String buyTransfer, String sellTransfer,
                        String buyCash, String sellCash, int flagResId) {
        this.currency = currency;
        this.buyTransfer = buyTransfer;
        this.sellTransfer = sellTransfer;
        this.buyCash = buyCash;
        this.sellCash = sellCash;
        this.flagResId = flagResId;
    }

    // Getter methods
    public String getCurrency() { return currency; }
    public String getBuyTransfer() { return buyTransfer; }
    public String getSellTransfer() { return sellTransfer; }
    public String getBuyCash() { return buyCash; }
    public String getSellCash() { return sellCash; }
    public int getFlagResId() { return flagResId; } // Thêm getter mới
}