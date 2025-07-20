--570

-- А в MySql можно в SELECT указывать и столбцы, которых нет в GROUP BY
SELECT a2.name
FROM Employee a1
         INNER JOIN Employee a2
                    ON a1.managerId = a2.id
GROUP BY a1.managerId
HAVING COUNT(*) > 4;


-- Подойдёт для Postgres
SELECT e2.name
FROM Employee e1
    INNER JOIN Employee e2
        ON e1.managerId = e2.id
GROUP BY e2.name, e1.managerId
HAVING COUNT(*) > 4;

-- другое решение
SELECT e.name name
FROM employee e
WHERE e.id in (SELECT e2.managerId
               FROM employee e2
               WHERE e2.managerId IS NOT NULL
               GROUP BY managerId
               HAVING COUNT(*) > 4)


