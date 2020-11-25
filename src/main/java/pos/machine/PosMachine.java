package pos.machine;

import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ReceiptItem> receiptItems = getReceiptItemsList(barcodes);

        return generateReceipt(receiptItems);
    }

    private String generateReceipt(List<ReceiptItem> receiptItems) {
        StringBuilder receipt = new StringBuilder("***<store earning no money>Receipt ***\r\n");

        for (ReceiptItem receiptItem : receiptItems) {
            receipt.append(generateProductDescriptionLine(receiptItem));
            receipt.append("\r\n");
        }

        receipt.append("----------------------\r\n");
        receipt.append(generateReceiptTotalAmountLine(receiptItems));
        receipt.append("\r\n");
        receipt.append("**********************");

        return receipt.toString();
    }

    private String generateReceiptTotalAmountLine(List<ReceiptItem> receiptItems) {
        Integer totalAmount = calculateTotalAmount(receiptItems);
        return String.format("Total: %s (yuan)", totalAmount);
    }

    private Integer calculateTotalAmount(List<ReceiptItem> receiptItems) {
        return receiptItems.stream().mapToInt(ReceiptItem::getSubtotal).sum();
    }

    private String generateProductDescriptionLine(ReceiptItem receiptItem) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)",
            receiptItem.getName(), receiptItem.getCount(), receiptItem.getPrice(), receiptItem.getSubtotal());
    }

    private List<ReceiptItem> getReceiptItemsList(List<String> barcodes) {
        List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
        Map<String, Integer> barcodeItemCountMap = countProductByBarcode(barcodes);
        List<ItemInfo> allProducts = findAllProducts();

        for (String barcode : barcodeItemCountMap.keySet()) {
            ItemInfo matchedProduct = findProductByBarcode(barcode, allProducts);
            if (matchedProduct == null) {
                continue;
            }

            receiptItems.add(new ReceiptItem(barcode, matchedProduct.getName(), matchedProduct.getPrice(), barcodeItemCountMap.get(barcode)));
        }

        return receiptItems;
    }

    private Map<String, Integer> countProductByBarcode(List<String> barcodes) {
        HashMap<String, Integer> barcodeItemCountMap = new HashMap<>();
        for (String barcode : barcodes) {
            if (barcodeItemCountMap.containsKey(barcode)) {
                barcodeItemCountMap.put(barcode, barcodeItemCountMap.get(barcode) + 1);
            } else {
                barcodeItemCountMap.put(barcode, 1);
            }
        }

        return barcodeItemCountMap;
    }

    private ItemInfo findProductByBarcode(String barcode, List<ItemInfo> allProducts) {
        return allProducts.stream().filter(product -> product.getBarcode().equals(barcode)).findAny().orElse(null);
    }

    private List<ItemInfo> findAllProducts() {
        return ItemDataLoader.loadAllItemInfos();
    }

}
