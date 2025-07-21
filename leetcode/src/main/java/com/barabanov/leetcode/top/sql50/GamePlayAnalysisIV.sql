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


-- решение для postgres
-- Write your PostgreSQL query statement below
WITH players_first_login AS (
    SELECT a.player_id player_id, MIN(event_date) player_first_event
    FROM activity a
    GROUP BY a.player_id)
SELECT ROUND(COUNT(*)::numeric / (SELECT COUNT(*)
                                  FROM players_first_login), 2) fraction
FROM activity a
WHERE (a.player_id, a.event_date - 1) IN (SELECT *
                                          FROM players_first_login)