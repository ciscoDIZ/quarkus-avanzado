export PRODUCT_INVENTORY='localhost:8080'
for sku in KE275 KE180 KE180 KE180 KE325 KE325 KE325 KE325 KI9K KE250 KE250 KE250 KE250 KI9K KE450 KE180 KE180 KI9K KE275 KE275 KE275 KEPATH04 KEPATH04 KE180 KE180 KI9K
do
     http patch $PRODUCT_INVENTORY'/products/'$sku'?stock=-500'
     echo
done