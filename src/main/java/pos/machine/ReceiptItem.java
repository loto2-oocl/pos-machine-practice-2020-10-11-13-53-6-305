package pos.machine;

public class ReceiptItem extends ItemInfo {
    private final int count;

    public ReceiptItem(String barcode, String name, int price, int count) {
        super(barcode, name, price);
        this.count = count;
    }

    public int getSubtotal() {
        return this.getCount() * this.getPrice();
    }

    public int getCount() {
        return count;
    }
}
