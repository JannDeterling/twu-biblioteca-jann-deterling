INSERT INTO book (id, title) VALUES (11, "The Pragmatic Programmer");
INSERT INTO member (id, name) VALUES (43, "Jann Deterling");
INSERT INTO checkout_item (member_id, book_id) VALUES (43, 11);

SELECT member.name FROM member WHERE  member.id IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book
    WHERE book.id = checkout_item.book_id AND book.title = "The Pragmatic Programmer"
);