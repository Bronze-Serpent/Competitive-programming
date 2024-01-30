--550
WITH
first_login AS(
    SELECT player_id, MIN(event_date)
    FROM Activity
    GROUP BY player_id
),
player_count AS(
    SELECT COUNT(*)
    FROM first_login
)
SELECT ROUND(COUNT(*) / (SELECT * FROM player_count), 2) fraction
FROM Activity
WHERE (player_id, DATE_SUB(event_date,  INTERVAL 1 DAY))
    IN (SELECT * FROM first_login)