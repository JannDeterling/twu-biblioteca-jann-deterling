SELECT member.name FROM member WHERE  member.id IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book
    WHERE book.id = checkout_item.book_id AND book.title = "The Hobbit"
);