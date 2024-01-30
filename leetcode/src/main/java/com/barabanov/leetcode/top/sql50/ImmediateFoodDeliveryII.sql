-- 1174

-- Решение с кучей подзапросов
WITH customer
         AS
         (SELECT DISTINCT customer_id
          FROM Delivery),
     first_order
         AS
         (SELECT
              (SELECT customer_id FROM Delivery d WHERE c.customer_id = d.customer_id ORDER BY order_date LIMIT 1) customer_id,
              (SELECT order_date FROM Delivery d WHERE c.customer_id = d.customer_id ORDER BY order_date LIMIT 1) order_date,
              (SELECT customer_pref_delivery_date FROM Delivery d WHERE c.customer_id = d.customer_id ORDER BY order_date LIMIT 1) customer_pref_delivery_date
          FROM customer c)
SELECT ROUND(SUM(IF(order_date = customer_pref_delivery_date, 1, 0)) / COUNT(*) * 100, 2) immediate_percentage
FROM first_order;


-- Человеческое решение :)
SELECT ROUND(SUM(IF(min_order_date = min_pref_date, 1, 0)) / COUNT(*) * 100, 2) immediate_percentage
FROM (SELECT customer_id,
             MIN(order_date) min_order_date,
             MIN(customer_pref_delivery_date) min_pref_date
      FROM Delivery
      GROUP BY customer_id) min_dates