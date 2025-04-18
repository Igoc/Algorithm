SELECT
    ed.YEAR AS YEAR,
    ed.MAX_SIZE_OF_COLONY - ed.SIZE_OF_COLONY AS YEAR_DEV,
    ed.ID AS ID
FROM (
    SELECT
        ID,
        SIZE_OF_COLONY,
        MAX(SIZE_OF_COLONY) OVER(PARTITION BY YEAR(DIFFERENTIATION_DATE)) AS MAX_SIZE_OF_COLONY,
        YEAR(DIFFERENTIATION_DATE) AS YEAR
    FROM ECOLI_DATA
) AS ed
ORDER BY YEAR, YEAR_DEV;
