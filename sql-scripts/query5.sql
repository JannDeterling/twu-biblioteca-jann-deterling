SELECT member.name FROM member WHERE member.id IN (
    SELECT checkout_item.member_id
    FROM checkout_item
    GROUP BY checkout_item.member_id
    HAVING count(checkout_item.movie_id) > 1 OR count(checkout_item.book_id) > 1
);