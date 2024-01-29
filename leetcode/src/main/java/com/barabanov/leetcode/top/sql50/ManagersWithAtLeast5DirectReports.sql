--570
SELECT a2.name
FROM Employee a1
    INNER JOIN Employee a2
        ON a1.managerId = a2.id
GROUP BY a1.managerId
HAVING COUNT(*) >= 5;



