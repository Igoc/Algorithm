SELECT DISTINCT cp1.CART_ID
FROM CART_PRODUCTS AS cp1
WHERE EXISTS (
    SELECT *
    FROM CART_PRODUCTS AS cp2
    WHERE cp1.CART_ID = cp2.CART_ID AND cp2.NAME = 'Milk'
) AND EXISTS (
    SELECT *
    FROM CART_PRODUCTS AS cp2
    WHERE cp1.CART_ID = cp2.CART_ID AND cp2.NAME = 'Yogurt'
)
ORDER BY cp1.CART_ID ASC;
