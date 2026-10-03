-- Table users
SELECT email, COUNT(*) AS occurence FROM users GROUP BY email HAVING occurence > 1;

WITH summary AS (SELECT *, COUNT(*) OVER(PARTITION BY email) AS email_occurence, RANK() OVER(PARTITION BY email ORDER BY id) AS id_order FROM users)

DELETE from summary WHERE email_occurence > 1 AND id_order = 1;





