SELECT book.title FROM book WHERE  book.id NOT IN (
    SELECT checkout_item.book_id
    FROM checkout_item, book, member
    WHERE checkout_item.book_id = book.id
        AND checkout_item.member_id = member.id
);
SELECT movie.title FROM movie WHERE  movie.id NOT IN (
    SELECT checkout_item.movie_id
    FROM checkout_item, movie, member
    WHERE checkout_item.movie_id = movie.id
        AND checkout_item.member_id = member.id
);