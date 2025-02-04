SELECT
    hrd.DEPT_ID,
    hrd.DEPT_NAME_EN,
    hre.AVG_SAL
FROM HR_DEPARTMENT AS hrd
    JOIN (
        SELECT
            DEPT_ID,
            ROUND(AVG(SAL)) AS AVG_SAL
        FROM HR_EMPLOYEES
        GROUP BY DEPT_ID
    ) AS hre ON hrd.DEPT_ID = hre.DEPT_ID
ORDER BY hre.AVG_SAL DESC;
