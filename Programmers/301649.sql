SELECT
    ed.ID AS ID,
    CASE
        WHEN ed.SIZE_OF_COLONY_PERCENTAGE <= 0.25 THEN 'CRITICAL'
        WHEN ed.SIZE_OF_COLONY_PERCENTAGE <= 0.5 THEN 'HIGH'
        WHEN ed.SIZE_OF_COLONY_PERCENTAGE <= 0.75 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME
FROM (
    SELECT
        ID,
        PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) AS SIZE_OF_COLONY_PERCENTAGE
    FROM ECOLI_DATA
) AS ed
ORDER BY ed.ID;
