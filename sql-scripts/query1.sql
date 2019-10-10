SELECT member.name FROM member WHERE  member.id IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book
    WHERE book.id = checkout_item.book_id AND book.title = "The Hobbit"
);

SELECT member.name
FROM  member, book
INNER JOIN checkout_Item ON checkout_Item.book_id = book.id AND checkout_Item.member_id = member.id
WHERE book.title = "The Hobbit";