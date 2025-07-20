-- 1934

-- ������� ��� JOIN
WITH confirms_stat
         AS
         (SELECT user_id,
                 CASE WHEN action IS NULL
                          THEN 'all_action'
                      ELSE action
                     END AS action_info,
                 COUNT(*) count
          FROM Confirmations
          GROUP BY user_id, action WITH ROLLUP),
     confirms_user_stat AS
         (SELECT user_id,
                 (SELECT count confirmed_count
                  FROM confirms_stat cs
                  WHERE cs.user_id = s.user_id AND action_info = 'confirmed')
                     /
                 (SELECT count confirmed_count
                  FROM confirms_stat cs
                  WHERE cs.user_id = s.user_id AND action_info = 'all_action') confirmation_rate
          FROM Signups s)
SELECT user_id,
       CASE WHEN confirmation_rate IS NULL
                THEN 0
            ELSE ROUND(confirmation_rate, 2)
           END AS confirmation_rate
FROM confirms_user_stat;



-- ������� � JOIN
SELECT s.user_id, ROUND(SUM(CASE
                                WHEN action = 'confirmed' THEN 1
                                ELSE 0
                            END) / COUNT(*), 2) confirmation_rate
FROM Signups s LEFT OUTER JOIN Confirmations c
                               ON s.user_id = c.user_id
GROUP BY user_id;


-- Такое же решение, что и выше но с использованием AVG, а не SUM
SELECT s.user_id user_id,
       ROUND(AVG(CASE
                     WHEN action = 'confirmed' THEN 1
                     ELSE 0
           END), 2) confirmation_rate
FROM signups s
         LEFT OUTER JOIN confirmations c
                         ON s.user_id = c.user_id
GROUP BY s.user_id;