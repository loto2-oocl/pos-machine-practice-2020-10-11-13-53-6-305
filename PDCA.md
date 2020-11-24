printReceipt
- P: 5min
- D: 5min
- C:
- A:

getReceiptItemsList
- P: 5min
- D: 10min
- C: looping more than expected
- A: not looping unique barcode to get receipt item, so loop with hashmap key set instead

countProductByBarcode
- P: 5min
- D: 10min
- C: want to add the count directly to iteminfo as the beginning
- A: used hashmap instead

findAllProducts
- P: 2min
- D: 2min
- C:
- A:

findProductByBarcode
- P: 5min
- D: 10min
- C: was originally using for loop to find product by barcode
- A: refactored to use stream api

generateReceipt
- P: 5min
- D: 5min
- C: 
- A:

generateProductDescriptionLine
- P: 2min
- D: 2min
- C:
- A:

generateReceiptTotalAmountLine
- P: 2min
- D: 2min
- C:
- A:

calculateTotalAmount
- P: 1min
- D: 1min
- C: N/A
- A: N/A

ReceiptItem.getSubtotal
- P: 1min
- D: 1min
- C: N/A
- A: N/A