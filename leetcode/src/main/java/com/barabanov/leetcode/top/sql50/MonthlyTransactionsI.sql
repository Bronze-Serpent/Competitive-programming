-- 1193
SELECT CONCAT(EXTRACT(YEAR FROM trans_date), '-', LPAD(EXTRACT(MONTH FROM trans_date), 2, 0)) month,
       country,
       COUNT(*) trans_count,
       SUM(CASE
               WHEN state = 'approved'
                   THEN 1
               ELSE 0
           END) approved_count,
       SUM(amount) trans_total_amount,
       SUM(CASE
               WHEN state = 'approved'
                   THEN amount
               ELSE 0
           END) approved_total_amount
FROM Transactions
GROUP BY country, month


-- для postgres:
SELECT to_char(t.trans_date, 'YYYY-MM') "month",
       t.country country,
       COUNT(*) trans_count,
       SUM(t.amount) trans_total_amount,
       COUNT(CASE
                 WHEN t.state = 'approved' THEN 1
           END) approved_count,
       SUM(CASE
               WHEN t.state = 'approved' THEN t.amount
               ELSE 0
           END) approved_total_amount
FROM transactions t
GROUP BY t.country, to_char(t.trans_date, 'YYYY-MM')