SELECT count(member.name) FROM member WHERE  member.id NOT IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book, movie, member
    WHERE checkout_item.member_id = member.id AND
        (book.id = checkout_item.book_id
        OR movie.id = checkout_item.movie_id)
);