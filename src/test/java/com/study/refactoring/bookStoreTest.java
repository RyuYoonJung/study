package com.study.refactoring;

import com.study.test.controller.dto.Book;
import com.study.test.controller.dto.User;
import com.study.test.controller.dto.UserBookList;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class bookStoreTest {

    @Test
    public void 도서관_대여_시스템(){
        List<User> userList = userList();
        List<Book> bookList = bookList();

        bookList.stream().filter(b -> b.getStock() == 0).forEach(b -> log.info(b.getName() + " 도서 재고 없음"));

        for (User user : userList) {
            log.info("--------------------------------------------------------------------");
            if (!isStatus(user)){
                List<UserBookList> userBookList = user.getBookList();
                for (UserBookList userBook : userBookList) {
                    int rentalCount = userBook.getRentalCount();
                    Book book = new Book();

                    for(Book value: bookList){
                        if(userBook.getName().equals(value.getName())){
                            book = value;
                        }
                    }

                    if(!isStock(book, rentalCount) && !isRentalCount(user, rentalCount)){
                        book.setStock(book.getStock() - rentalCount);
                        user.setRentalCount(user.getRentalCount() + rentalCount);

                        double discount = 0;
                        if(user.getGrade().equals("00")){
                            discount = 0;
                        } else if(user.getGrade().equals("01")){
                            discount = 5;
                        } else if(user.getGrade().equals("02")){
                            discount = 10;
                        } else if(user.getGrade().equals("03")){
                            discount = 15;
                        } else if(user.getGrade().equals("04")){
                            discount = 20;
                        } else if(user.getGrade().equals("05")){
                            discount = 25;
                        }

                        int price = book.getPrice();
                        if(discount != 0) {
                            price = (int) (book.getPrice() - book.getPrice()*(discount/100));
                        }
                        log.info("가격 : " + price);

                        if(user.getRentalCount() == 5) {
                            user.setStatus("01");
                        }

                        int returnCount = 3;
                        user.setRentalCount(user.getRentalCount() - returnCount);
                        book.setStock(book.getStock() + returnCount);

                        if(user.getRentalCount() != 5) {
                            user.setStatus("00");
                        }
                        log.info(user.toString());
                        log.info(book.toString());

                    }
                }
                }
            log.info("--------------------------------------------------------------------");
        }
        //회원 대여 체크

    }

    @Test
    public void 대출_불가_도서확인() {
        List<bookName> resultList = bookList().stream().filter(b -> b.getStock() == 0).map(bookName::new).collect(toList());
        log.info(resultList.toString());
    }

    @Test
    public void 대출_불가_도서확인_테스트() {
        Book book1 = new Book("book1", 5, 10000);
        Book book2 = new Book("book2", 0, 40000);
        Book book3 = new Book("book3", 0, 30000);
        Book book4 = new Book("book4", 10, 20000);
        bookTest(book1, book2, book3, book4, book4);
    }

    private static void bookTest(Book... bookList) {
        Arrays.stream(bookList).collect(toList()).forEach(b -> log.info(b.toString()));
    }

    private boolean isStock(Book book, int rentalCount){
        boolean result = false;
        if(book.getStock() < rentalCount) {
            log.info(book.getName() + " 재고 초과");
            result = true;
        }
        return result;
    }

    private boolean isRentalCount(User user, int rentalCount){
        boolean result = false;
        if(user.getRentalCount() + rentalCount > 5) {
            log.info(user.getName() + " 대여 개수 초과");
            result = true;
        }
        return result;
    }

    private boolean isStatus(User user){
        boolean result = false;
        if (user.getStatus().equals("01")) {
            log.info(user.getName() + " 도서 대여 불가");
            result = true;
        }
        return result;
    }

    @Data
    static class bookName {
        private String name;

        public bookName(Book book){
            this.name = book.getName();
        }
    }

    @Test
    public void 책_재고_체크(){
        bookList().stream().filter(b -> b.getStock() == 0).forEach(b -> log.info(b.getName() + " 도서 재고 없음"));
    }

    private static List<Book> bookList() {
        List<Book> resultList = new ArrayList<>();
        Book book1 = new Book("book1", 5, 10000);
        resultList.add(book1);
        Book book2 = new Book("book2", 0, 40000);
        resultList.add(book2);
        Book book3 = new Book("book3", 0, 30000);
        resultList.add(book3);
        Book book4 = new Book("book4", 10, 20000);
        resultList.add(book4);
        return resultList;
    }

    private static List<User> userList() {
        List<User> resultList = new ArrayList<>();
        User user1 = new User("member1", 2, "00", "03", userBookList());
        resultList.add(user1);
        User user2 = new User("member2", 5, "01", "03", userBookList());
        resultList.add(user2);
        User user3 = new User("member3", 3, "00", "02", userBookList());
        resultList.add(user3);
        User user4 = new User("member4", 2, "00", "05", userBookList());
        resultList.add(user4);
        User user5 = new User("member5", 3, "00", "01", userBookList());
        resultList.add(user5);
        return resultList;
    }

    private static List<UserBookList> userBookList() {
        List<UserBookList> resultList = new ArrayList<>();
        resultList.add(new UserBookList("book1", 2));
        resultList.add(new UserBookList("book2", 1));
        return resultList;
    }
}

