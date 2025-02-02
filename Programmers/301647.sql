SELECT c.ID AS ID, c.GENOTYPE AS GENOTYPE, p.GENOTYPE AS PARENT_GENOTYPE
FROM ECOLI_DATA AS c
    JOIN ECOLI_DATA AS p ON c.PARENT_ID = p.ID
WHERE c.GENOTYPE & p.GENOTYPE = p.GENOTYPE
ORDER BY c.ID;
