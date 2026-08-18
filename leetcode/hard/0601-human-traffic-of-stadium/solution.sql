SELECT
    id,
    visit_date,
    people
FROM (
    SELECT
        id,
        visit_date,
        people,
        LAG(id, 1) OVER (ORDER BY id) AS prev_id,
        LAG(id, 2) OVER (ORDER BY id) AS prev2_id,
        LEAD(id, 1) OVER (ORDER BY id) AS next_id,
        LEAD(id, 2) OVER (ORDER BY id) AS next2_id,
        LAG(people, 1) OVER (ORDER BY id) AS prev_people,
        LAG(people, 2) OVER (ORDER BY id) AS prev2_people,
        LEAD(people, 1) OVER (ORDER BY id) AS next_people,
        LEAD(people, 2) OVER (ORDER BY id) AS next2_people
    FROM Stadium
) t
WHERE
    (
        people >= 100
        AND prev_id = id - 1
        AND prev2_id = id - 2
        AND prev_people >= 100
        AND prev2_people >= 100
    )
    OR
    (
        people >= 100
        AND prev_id = id - 1
        AND next_id = id + 1
        AND prev_people >= 100
        AND next_people >= 100
    )
    OR
    (
        people >= 100
        AND next_id = id + 1
        AND next2_id = id + 2
        AND next_people >= 100
        AND next2_people >= 100
    )
ORDER BY visit_date;
