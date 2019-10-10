1. Who checked out the book 'The Hobbit’?
- Anand Beck
```sql
SELECT member.name FROM member WHERE  member.id IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book
    WHERE book.id = checkout_item.book_id AND book.title = "The Hobbit"
);
--- Solution using an inner join ---
SELECT member.name
FROM  member, book
INNER JOIN checkout_Item ON checkout_Item.book_id = book.id AND checkout_Item.member_id = member.id
WHERE book.title = "The Hobbit";
```

2. How many people have not checked out anything?
- 37
```sql
SELECT count(member.name) FROM member WHERE  member.id NOT IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book, movie, member
    WHERE checkout_item.member_id = member.id AND
        (book.id = checkout_item.book_id
        OR movie.id = checkout_item.movie_id)
);
```

3. What books and movies aren't checked out?
- Fellowship of the Ring
- 1984
- Tom Sawyer
- Catcher in the Rye
- To Kill a Mockingbird
- Domain Driven Design
- Thin Red Line
- Crouching Tiger, Hidden Dragon
- Lawrence of Arabia
- Office Space

```sql
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
```

4. Add the book 'The Pragmatic Programmer', and add yourself as a member. Check out 'The Pragmatic Programmer'. Use your query from question 1 to verify that you have checked it out. Also, provide the SQL used to update the database.
- Jann Deterling
```sql
INSERT INTO book (id, title) VALUES (11, "The Pragmatic Programmer");
INSERT INTO member (id, name) VALUES (43, "Jann Deterling");
INSERT INTO checkout_item (member_id, book_id) VALUES (43, 11);

SELECT member.name FROM member WHERE  member.id IN (
    SELECT checkout_item.member_id
    FROM checkout_item, book
    WHERE book.id = checkout_item.book_id AND book.title = "The Pragmatic Programmer"
);
```
5. Who has checked out more than 1 item? 
- Anand Beck
- Frank Smith

```sql
SELECT member.name FROM member WHERE member.id IN (
    SELECT checkout_item.member_id
    FROM checkout_item
    GROUP BY checkout_item.member_id
    HAVING count(checkout_item.movie_id) > 1 OR count(checkout_item.book_id) > 1
);
```