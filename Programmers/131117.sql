SELECT fp.PRODUCT_ID, fp.PRODUCT_NAME, fp.PRICE * SUM(fo.AMOUNT) AS TOTAL_SALES
FROM FOOD_PRODUCT AS fp
    JOIN FOOD_ORDER AS fo ON fp.PRODUCT_ID = fo.PRODUCT_ID
WHERE YEAR(fo.PRODUCE_DATE) = 2022 AND MONTH(fo.PRODUCE_DATE) = 5
GROUP BY fp.PRODUCT_ID
ORDER BY TOTAL_SALES DESC, fp.PRODUCT_ID ASC;