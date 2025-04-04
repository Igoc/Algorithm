SELECT
    COUNT(*) AS FISH_COUNT,
    MAX(fi.LENGTH) AS MAX_LENGTH,
    fi.FISH_TYPE AS FISH_TYPE
FROM (
    SELECT
        FISH_TYPE,
        CASE
            WHEN LENGTH IS NULL THEN 10
            ELSE LENGTH
        END AS LENGTH
    FROM FISH_INFO
) AS fi
GROUP BY fi.FISH_TYPE
HAVING AVG(fi.LENGTH) >= 33
ORDER BY fi.FISH_TYPE;
