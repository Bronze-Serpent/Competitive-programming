-- решение без JOIN
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

-- решение с JOIN
